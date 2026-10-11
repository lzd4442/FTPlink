package com.ftplink.data.repository

import com.ftplink.data.local.ServerDao
import com.ftplink.data.local.ServerEntity
import com.ftplink.data.remote.FtpClientManager
import com.ftplink.domain.model.RemoteFile
import com.ftplink.domain.model.ServerConfig
import com.ftplink.domain.model.ServerType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.io.InputStream
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ServerRepository @Inject constructor(
    private val serverDao: ServerDao,
    private val ftpClientManager: FtpClientManager
) {
    fun getAllServers(): Flow<List<ServerConfig>> =
        serverDao.getAllServers().map { entities ->
            entities.map { it.toServerConfig() }
        }

    suspend fun getServerById(id: Long): ServerConfig? =
        serverDao.getServerById(id)?.toServerConfig()

    suspend fun saveServer(server: ServerConfig): Long =
        serverDao.insertServer(server.toEntity())

    suspend fun updateServer(server: ServerConfig) =
        serverDao.updateServer(server.toEntity())

    suspend fun deleteServer(id: Long) =
        serverDao.deleteServerById(id)

    suspend fun connect(server: ServerConfig): Result<Unit> =
        ftpClientManager.connect(
            host = server.host,
            port = server.port,
            username = server.username,
            password = server.password,
            type = server.type.name
        )

    suspend fun listFiles(path: String): Result<List<RemoteFile>> =
        ftpClientManager.listFiles(path)

    suspend fun downloadFile(remotePath: String, localPath: String): Result<Unit> =
        ftpClientManager.downloadFile(remotePath, localPath)

    suspend fun getInputStream(remotePath: String): Result<InputStream> =
        ftpClientManager.getInputStream(remotePath)

    suspend fun rename(oldPath: String, newPath: String): Result<Unit> =
        ftpClientManager.rename(oldPath, newPath)

    suspend fun delete(path: String, isDirectory: Boolean): Result<Unit> =
        ftpClientManager.delete(path, isDirectory)

    suspend fun createDirectory(path: String): Result<Unit> =
        ftpClientManager.createDirectory(path)

    fun disconnect() = ftpClientManager.disconnect()

    val isConnected: Boolean
        get() = ftpClientManager.isConnected

    private fun ServerEntity.toServerConfig() = ServerConfig(
        id = id,
        name = name,
        host = host,
        port = port,
        username = username,
        password = password,
        type = ServerType.valueOf(type),
        lastPath = lastPath,
        createdAt = createdAt
    )

    private fun ServerConfig.toEntity() = ServerEntity(
        id = id,
        name = name,
        host = host,
        port = port,
        username = username,
        password = password,
        type = type.name,
        lastPath = lastPath,
        createdAt = createdAt
    )
}
