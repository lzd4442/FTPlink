package com.ftplink.ui.screens

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ftplink.data.local.SettingDao
import com.ftplink.data.local.SettingEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingDao: SettingDao,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _cacheSize = MutableStateFlow(64)
    val cacheSize: StateFlow<Int> = _cacheSize.asStateFlow()

    private val _isClearing = MutableStateFlow(false)
    val isClearing: StateFlow<Boolean> = _isClearing.asStateFlow()

    init {
        loadSettings()
    }

    private fun loadSettings() {
        viewModelScope.launch {
            settingDao.getValue("cache_size")?.let {
                _cacheSize.value = it.toIntOrNull() ?: 64
            }
        }
    }

    fun setCacheSize(size: Int) {
        _cacheSize.value = size
        viewModelScope.launch {
            settingDao.setValue(SettingEntity("cache_size", size.toString()))
        }
    }

    fun clearCache() {
        viewModelScope.launch {
            _isClearing.value = true
            withContext(Dispatchers.IO) {
                val cacheDir = File(context.cacheDir, "ftplink_temp")
                if (cacheDir.exists()) {
                    cacheDir.deleteRecursively()
                }
            }
            _isClearing.value = false
        }
    }
}
