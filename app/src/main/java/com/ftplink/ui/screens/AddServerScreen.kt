package com.ftplink.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ftplink.R
import com.ftplink.domain.model.ServerType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddServerScreen(
    onNavigateBack: () -> Unit,
    viewModel: AddServerViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var showPassword by remember { mutableStateOf(false) }
    var selectedType by remember { mutableStateOf(ServerType.FTP) }

    LaunchedEffect(uiState.isSaved) {
        if (uiState.isSaved) {
            onNavigateBack()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.add_server)) },
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
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // 服务器类型选择
            Text(stringResource(R.string.server_type), style = MaterialTheme.typography.labelLarge)
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FilterChip(
                    selected = selectedType == ServerType.FTP,
                    onClick = { selectedType = ServerType.FTP },
                    label = { Text("FTP") },
                    leadingIcon = {
                        Icon(Icons.Default.Cloud, contentDescription = null)
                    }
                )
                FilterChip(
                    selected = selectedType == ServerType.SFTP,
                    onClick = { selectedType = ServerType.SFTP },
                    label = { Text("SFTP") },
                    leadingIcon = {
                        Icon(Icons.Default.Security, contentDescription = null)
                    }
                )
            }

            OutlinedTextField(
                value = uiState.name,
                onValueChange = viewModel::updateName,
                label = { Text(stringResource(R.string.server_name)) },
                placeholder = { Text(stringResource(R.string.server_name_hint)) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = uiState.host,
                onValueChange = viewModel::updateHost,
                label = { Text(stringResource(R.string.host)) },
                placeholder = { Text("ftp.example.com") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Uri)
            )

            OutlinedTextField(
                value = uiState.port,
                onValueChange = viewModel::updatePort,
                label = { Text(stringResource(R.string.port)) },
                placeholder = { Text(if (selectedType == ServerType.FTP) "21" else "22") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            OutlinedTextField(
                value = uiState.username,
                onValueChange = viewModel::updateUsername,
                label = { Text(stringResource(R.string.username)) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = uiState.password,
                onValueChange = viewModel::updatePassword,
                label = { Text(stringResource(R.string.password)) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { showPassword = !showPassword }) {
                        Icon(
                            if (showPassword) Icons.Default.Security else Icons.Default.Security,
                            contentDescription = null
                        )
                    }
                }
            )

            if (uiState.error != null) {
                Text(
                    uiState.error!!,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { viewModel.saveServer(selectedType) },
                modifier = Modifier.fillMaxWidth(),
                enabled = uiState.name.isNotBlank() && uiState.host.isNotBlank()
            ) {
                Text(stringResource(R.string.save))
            }
        }
    }
}
