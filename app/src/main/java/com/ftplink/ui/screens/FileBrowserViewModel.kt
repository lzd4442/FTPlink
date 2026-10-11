package com.ftplink.ui.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ftplink.data.repository.ServerRepository
import com.ftplink.domain.model.RemoteFile
import com.ftplink.domain.model.ServerConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import javax.inject.Inject

data class FileBrowserUiState(
    val serverName: String? = null,
    val currentPath: String = "/",
    val files: List<RemoteFile> = emptyList(),
    val isLoading: Boolean = false,
    val isDownloading: Boolean = false,
    val error: String? = null,
    val disconnected: Boolean = false,
    val openFileIntent: Intent? = null
)

@HiltViewModel
class FileBrowserViewModel @Inject constructor(
    private val repository: ServerRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(FileBrowserUiState())
    val uiState: StateFlow<FileBrowserUiState> = _uiState.asStateFlow()

    private var currentServer: ServerConfig? = null
    private val pathStack = mutableListOf<String>()

    fun loadServer(serverId: Long) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            
            val server = repository.getServerById(serverId)
            if (server == null) {
                _uiState.update { it.copy(error = "服务器不存在", isLoading = false) }
                return@launch
            }
            
            currentServer = server
            _uiState.update { it.copy(serverName = server.name) }
            
            repository.connect(server).fold(
                onSuccess = {
                    loadFiles(server.lastPath)
                },
                onFailure = { e ->
                    _uiState.update { 
                        it.copy(error = "连接失败: ${e.message}", isLoading = false) 
                    }
                }
            )
        }
    }

    fun refresh() {
        loadFiles(_uiState.value.currentPath)
    }

    fun navigateTo(path: String) {
        val current = _uiState.value.currentPath
        if (path.startsWith(current)) {
            pathStack.add(current)
        } else {
            pathStack.clear()
        }
        loadFiles(path)
    }

    fun goBack(): Boolean {
        if (pathStack.isNotEmpty()) {
            loadFiles(pathStack.removeLast())
            return true
        }
        return false
    }

    private fun loadFiles(path: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            
            repository.listFiles(path).fold(
                onSuccess = { files ->
                    _uiState.update { 
                        it.copy(
                            currentPath = path,
                            files = files.sortedWith(compareBy({ !it.isDirectory }, { it.name.lowercase() })),
                            isLoading = false
                        )
                    }
                },
                onFailure = { e ->
                    _uiState.update { 
                        it.copy(error = "加载失败: ${e.message}", isLoading = false) 
                    }
                }
            )
        }
    }

    fun createFolder(name: String) {
        viewModelScope.launch {
            val path = _uiState.value.currentPath
            val newPath = if (path.endsWith("/")) "$path$name" else "$path/$name"
            
            repository.createDirectory(newPath).fold(
                onSuccess = { refresh() },
                onFailure = { e ->
                    _uiState.update { it.copy(error = "创建失败: ${e.message}") }
                }
            )
        }
    }

    fun rename(file: RemoteFile, newName: String) {
        viewModelScope.launch {
            val parentPath = file.path.substringBeforeLast("/", "")
            val newPath = if (parentPath.isEmpty()) "/$newName" else "$parentPath/$newName"
            
            repository.rename(file.path, newPath).fold(
                onSuccess = { refresh() },
                onFailure = { e ->
                    _uiState.update { it.copy(error = "重命名失败: ${e.message}") }
                }
            )
        }
    }

    fun delete(file: RemoteFile) {
        viewModelScope.launch {
            repository.delete(file.path, file.isDirectory).fold(
                onSuccess = { refresh() },
                onFailure = { e ->
                    _uiState.update { it.copy(error = "删除失败: ${e.message}") }
                }
            )
        }
    }

    fun downloadAndOpen(context: Context, file: RemoteFile) {
        viewModelScope.launch {
            _uiState.update { it.copy(isDownloading = true) }
            
            try {
                val cacheDir = File(context.cacheDir, "ftplink_temp")
                if (!cacheDir.exists()) cacheDir.mkdirs()
                
                val localFile = File(cacheDir, file.name)
                
                withContext(Dispatchers.IO) {
                    repository.downloadFile(file.path, localFile.absolutePath).getOrThrow()
                }
                
                val mimeType = getMimeType(file.extension)
                val uri = FileProvider.getUriForFile(
                    context,
                    "${context.packageName}.fileprovider",
                    localFile
                )
                
                val intent = if (file.isPreviewable) {
                    Intent(Intent.ACTION_VIEW).apply {
                        setDataAndType(uri, mimeType)
                        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    }
                } else {
                    Intent(Intent.ACTION_VIEW).apply {
                        data = uri
                        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    }
                }
                
                val chooser = Intent.createChooser(intent, context.getString(com.ftplink.R.string.open_with))
                chooser.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                
                _uiState.update { it.copy(isDownloading = false) }
                context.startActivity(chooser)
                
            } catch (e: Exception) {
                _uiState.update { 
                    it.copy(isDownloading = false, error = "打开失败: ${e.message}") 
                }
            }
        }
    }

    private fun getMimeType(extension: String): String {
        return when (extension.lowercase()) {
            "jpg", "jpeg" -> "image/jpeg"
            "png" -> "image/png"
            "gif" -> "image/gif"
            "webp" -> "image/webp"
            "bmp" -> "image/bmp"
            "mp4" -> "video/mp4"
            "avi" -> "video/x-msvideo"
            "mkv" -> "video/x-matroska"
            "mov" -> "video/quicktime"
            "mp3" -> "audio/mpeg"
            "wav" -> "audio/wav"
            "ogg" -> "audio/ogg"
            "pdf" -> "application/pdf"
            "txt" -> "text/plain"
            "json" -> "application/json"
            "xml" -> "application/xml"
            "html" -> "text/html"
            "zip" -> "application/zip"
            else -> "*/*"
        }
    }

    fun disconnect() {
        repository.disconnect()
        _uiState.update { it.copy(disconnected = true) }
    }

    override fun onCleared() {
        super.onCleared()
        repository.disconnect()
    }
}
