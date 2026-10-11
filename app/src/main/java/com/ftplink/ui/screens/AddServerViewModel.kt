package com.ftplink.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ftplink.data.repository.ServerRepository
import com.ftplink.domain.model.ServerConfig
import com.ftplink.domain.model.ServerType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AddServerUiState(
    val name: String = "",
    val host: String = "",
    val port: String = "21",
    val username: String = "",
    val password: String = "",
    val error: String? = null,
    val isSaved: Boolean = false
)

@HiltViewModel
class AddServerViewModel @Inject constructor(
    private val repository: ServerRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddServerUiState())
    val uiState: StateFlow<AddServerUiState> = _uiState.asStateFlow()

    fun updateName(name: String) = _uiState.update { it.copy(name = name) }
    fun updateHost(host: String) = _uiState.update { it.copy(host = host, error = null) }
    fun updatePort(port: String) = _uiState.update { it.copy(port = port) }
    fun updateUsername(username: String) = _uiState.update { it.copy(username = username) }
    fun updatePassword(password: String) = _uiState.update { it.copy(password = password) }

    fun saveServer(type: ServerType) {
        val state = _uiState.value
        
        if (state.host.isBlank()) {
            _uiState.update { it.copy(error = "请输入服务器地址") }
            return
        }
        
        viewModelScope.launch {
            val port = state.port.toIntOrNull() ?: if (type == ServerType.FTP) 21 else 22
            val server = ServerConfig(
                name = state.name.ifBlank { state.host },
                host = state.host,
                port = port,
                username = state.username,
                password = state.password,
                type = type
            )
            repository.saveServer(server)
            _uiState.update { it.copy(isSaved = true) }
        }
    }
}
