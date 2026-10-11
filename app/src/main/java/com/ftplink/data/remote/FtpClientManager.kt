package com.ftplink.data.remote

import com.ftplink.domain.model.RemoteFile
import com.jcraft.jsch.*
import org.apache.commons.net.ftp.FTP
import org.apache.commons.net.ftp.FTPClient
import org.apache.commons.net.ftp.FTPFile
import java.io.InputStream
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FtpClientManager @Inject constructor() {

    private var ftpClient: FTPClient? = null
    private var sftpChannel: ChannelSftp? = null
    private var jsch: JSch? = null

    var isConnected: Boolean = false
        private set

    var currentServerType: String = "FTP"
        private set

    suspend fun connect(
        host: String,
        port: Int,
        username: String,
        password: String,
        type: String
    ): Result<Unit> = kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.IO) {
        try {
            currentServerType = type
            if (type == "SFTP") {
                connectSftp(host, port, username, password)
            } else {
                connectFtp(host, port, username, password)
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun connectFtp(host: String, port: Int, username: String, password: String) {
        ftpClient = FTPClient().apply {
            connect(host, port)
            login(username, password)
            enterLocalPassiveMode()
            setFileType(FTP.BINARY_FILE_TYPE)
        }
        isConnected = true
    }

    private fun connectSftp(host: String, port: Int, username: String, password: String) {
        jsch = JSch()
        val session = jsch?.getSession(username, host, port)
        session?.setPassword(password)
        session?.setConfig("StrictHostKeyChecking", "no")
        session?.connect()
        val channel = session?.openChannel("sftp")
        channel?.connect()
        sftpChannel = channel as? ChannelSftp
        isConnected = true
    }

    suspend fun listFiles(path: String): Result<List<RemoteFile>> = 
        kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.IO) {
            try {
                val files = if (currentServerType == "SFTP") {
                    listSftpFiles(path)
                } else {
                    listFtpFiles(path)
                }
                Result.success(files)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }

    private fun listFtpFiles(path: String): List<RemoteFile> {
        val client = ftpClient ?: throw IllegalStateException("Not connected")
        client.changeWorkingDirectory(path)
        val ftpFiles = client.listFiles()
        return ftpFiles.filter { it.name != "." && it.name != ".." }.map { it.toRemoteFile(path) }
    }

    private fun listSftpFiles(path: String): List<RemoteFile> {
        val channel = sftpChannel ?: throw IllegalStateException("Not connected")
        val files = channel.ls(path)
        return files.filter { 
            (it as? ChannelSftp.LsEntry)?.filename != "." && 
            (it as? ChannelSftp.LsEntry)?.filename != ".."
        }.map { (it as ChannelSftp.LsEntry).toRemoteFile(path) }
    }

    suspend fun downloadFile(remotePath: String, localPath: String): Result<Unit> =
        kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.IO) {
            try {
                if (currentServerType == "SFTP") {
                    sftpChannel?.get(remotePath, localPath)
                } else {
                    ftpClient?.retrieveFile(remotePath, java.io.FileOutputStream(localPath))
                }
                Result.success(Unit)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }

    suspend fun getInputStream(remotePath: String): Result<InputStream> =
        kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.IO) {
            try {
                val inputStream = if (currentServerType == "SFTP") {
                    sftpChannel?.get(remotePath)?.let { 
                        object : InputStream() {
                            private val bytes = it.readBytes()
                            private var pos = 0
                            override fun read(): Int = if (pos < bytes.size) (bytes[pos++].toInt() and 0xFF) else -1
                        }
                    }
                } else {
                    ftpClient?.retrieveFileStream(remotePath)
                } ?: throw IllegalStateException("Not connected")
                Result.success(inputStream!!)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }

    suspend fun rename(oldPath: String, newPath: String): Result<Unit> =
        kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.IO) {
            try {
                if (currentServerType == "SFTP") {
                    sftpChannel?.rename(oldPath, newPath)
                } else {
                    ftpClient?.rename(oldPath, newPath)
                }
                Result.success(Unit)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }

    suspend fun delete(path: String, isDirectory: Boolean): Result<Unit> =
        kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.IO) {
            try {
                if (currentServerType == "SFTP") {
                    if (isDirectory) sftpChannel?.rmdir(path) else sftpChannel?.rm(path)
                } else {
                    ftpClient?.deleteFile(path)
                }
                Result.success(Unit)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }

    suspend fun createDirectory(path: String): Result<Unit> =
        kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.IO) {
            try {
                if (currentServerType == "SFTP") {
                    sftpChannel?.mkdir(path)
                } else {
                    ftpClient?.makeDirectory(path)
                }
                Result.success(Unit)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }

    fun disconnect() {
        try {
            sftpChannel?.disconnect()
            ftpClient?.logout()
            ftpClient?.disconnect()
        } catch (_: Exception) {}
        isConnected = false
    }

    private fun FTPFile.toRemoteFile(parentPath: String) = RemoteFile(
        name = name,
        path = "$parentPath/${name}".replace("//", "/"),
        isDirectory = isDirectory,
        size = size,
        lastModified = timestamp.timeInMillis,
        permissions = ""
    )

    private fun ChannelSftp.LsEntry.toRemoteFile(parentPath: String) = RemoteFile(
        name = filename,
        path = "$parentPath/$filename".replace("//", "/"),
        isDirectory = attrs.isDir,
        size = attrs.size,
        lastModified = attrs.mTime * 1000L,
        permissions = ""
    )
}
