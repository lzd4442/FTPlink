package com.ftplink.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ftplink.data.repository.ServerRepository
import com.ftplink.domain.model.ServerConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ServerListViewModel @Inject constructor(
    private val repository: ServerRepository
) : ViewModel() {

    private val _servers = MutableStateFlow<List<ServerConfig>>(emptyList())
    val servers: StateFlow<List<ServerConfig>> = _servers.asStateFlow()

    fun loadServers() {
        viewModelScope.launch {
            repository.getAllServers().collect { list ->
                _servers.value = list
            }
        }
    }

    fun deleteServer(id: Long) {
        viewModelScope.launch {
            repository.deleteServer(id)
        }
    }
}
