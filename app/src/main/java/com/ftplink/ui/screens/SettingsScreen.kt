package com.ftplink.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ftplink.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onNavigateBack: () -> Unit,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val cacheSize by viewModel.cacheSize.collectAsState()
    val isClearing by viewModel.isClearing.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.settings)) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = stringResource(R.string.back))
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            // 缓存区设置
            ListItem(
                headlineContent = { Text(stringResource(R.string.cache_size)) },
                supportingContent = { Text(stringResource(R.string.cache_size_desc)) }
            )
            
            var sliderValue by remember(cacheSize) { mutableFloatStateOf(cacheSize.toFloat()) }
            
            Column(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("16 MB", style = MaterialTheme.typography.bodySmall)
                    Text(
                        "${sliderValue.toInt()} MB",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text("512 MB", style = MaterialTheme.typography.bodySmall)
                }
                
                Slider(
                    value = sliderValue,
                    onValueChange = { sliderValue = it },
                    onValueChangeFinished = {
                        viewModel.setCacheSize(sliderValue.toInt())
                    },
                    valueRange = 16f..512f,
                    steps = 30,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Divider(modifier = Modifier.padding(vertical = 8.dp))

            // 清理缓存
            ListItem(
                headlineContent = { Text(stringResource(R.string.clear_cache)) },
                supportingContent = { Text(stringResource(R.string.clear_cache_desc)) },
                trailingContent = {
                    Button(
                        onClick = { viewModel.clearCache() },
                        enabled = !isClearing
                    ) {
                        if (isClearing) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(16.dp),
                                strokeWidth = 2.dp
                            )
                        } else {
                            Text(stringResource(R.string.clear))
                        }
                    }
                }
            )

            Divider(modifier = Modifier.padding(vertical = 8.dp))

            // 关于
            ListItem(
                headlineContent = { Text(stringResource(R.string.about)) },
                supportingContent = { Text("FTPlink v1.0.0") }
            )
        }
    }
}
