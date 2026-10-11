package com.ftplink.ui.screens

import android.Manifest
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ftplink.R
import com.ftplink.domain.model.RemoteFile
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FileBrowserScreen(
    serverId: Long,
    onNavigateBack: () -> Unit,
    viewModel: FileBrowserViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    var showNewFolderDialog by remember { mutableStateOf(false) }
    var showRenameDialog by remember { mutableStateOf<RemoteFile?>(null) }
    var showDeleteDialog by remember { mutableStateOf<RemoteFile?>(null) }
    var showOperationMenu by remember { mutableStateOf(false) }
    var selectedFile by remember { mutableStateOf<RemoteFile?>(null) }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) {}

    LaunchedEffect(serverId) {
        permissionLauncher.launch(arrayOf(Manifest.permission.INTERNET))
        viewModel.loadServer(serverId)
    }

    LaunchedEffect(uiState.disconnected) {
        if (uiState.disconnected) onNavigateBack()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(uiState.serverName ?: "FTPlink") },
                navigationIcon = {
                    IconButton(onClick = {
                        viewModel.disconnect()
                        onNavigateBack()
                    }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = stringResource(R.string.back))
                    }
                },
                actions = {
                    IconButton(onClick = { showOperationMenu = true }) {
                        Icon(Icons.Default.MoreVert, contentDescription = null)
                    }
                    DropdownMenu(
                        expanded = showOperationMenu,
                        onDismissRequest = { showOperationMenu = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text(stringResource(R.string.new_folder)) },
                            onClick = {
                                showOperationMenu = false
                                showNewFolderDialog = true
                            },
                            leadingIcon = { Icon(Icons.Default.CreateNewFolder, null) }
                        )
                        DropdownMenuItem(
                            text = { Text(stringResource(R.string.refresh)) },
                            onClick = {
                                showOperationMenu = false
                                viewModel.refresh()
                            },
                            leadingIcon = { Icon(Icons.Default.Refresh, null) }
                        )
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
            // 路径导航
            PathBar(
                path = uiState.currentPath,
                onPathClick = { path -> viewModel.navigateTo(path) }
            )

            when {
                uiState.isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                uiState.error != null -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                Icons.Default.ErrorOutline,
                                contentDescription = null,
                                modifier = Modifier.size(48.dp),
                                tint = MaterialTheme.colorScheme.error
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(uiState.error!!, color = MaterialTheme.colorScheme.error)
                            Spacer(modifier = Modifier.height(16.dp))
                            Button(onClick = { viewModel.refresh() }) {
                                Text(stringResource(R.string.retry))
                            }
                        }
                    }
                }
                uiState.files.isEmpty() -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            stringResource(R.string.empty_folder),
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                        )
                    }
                }
                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(vertical = 8.dp)
                    ) {
                        items(uiState.files, key = { it.path }) { file ->
                            FileListItem(
                                file = file,
                                onClick = {
                                    if (file.isDirectory) {
                                        viewModel.navigateTo(file.path)
                                    } else {
                                        selectedFile = file
                                        viewModel.downloadAndOpen(context, file)
                                    }
                                },
                                onLongClick = { selectedFile = file },
                                onRename = { showRenameDialog = file },
                                onDelete = { showDeleteDialog = file }
                            )
                        }
                    }
                }
            }

            // 下载进度
            if (uiState.isDownloading) {
                LinearProgressIndicator(
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }

    // 新建文件夹对话框
    if (showNewFolderDialog) {
        NewFolderDialog(
            onDismiss = { showNewFolderDialog = false },
            onConfirm = { name ->
                viewModel.createFolder(name)
                showNewFolderDialog = false
            }
        )
    }

    // 重命名对话框
    showRenameDialog?.let { file ->
        RenameDialog(
            oldName = file.name,
            onDismiss = { showRenameDialog = null },
            onConfirm = { newName ->
                viewModel.rename(file, newName)
                showRenameDialog = null
            }
        )
    }

    // 删除确认对话框
    showDeleteDialog?.let { file ->
        DeleteConfirmDialog(
            fileName = file.name,
            onDismiss = { showDeleteDialog = null },
            onConfirm = {
                viewModel.delete(file)
                showDeleteDialog = null
            }
        )
    }
}

@Composable
fun PathBar(
    path: String,
    onPathClick: (String) -> Unit
) {
    val parts = path.split("/").filter { it.isNotEmpty() }
    val context = LocalContext.current
    val cacheDir = context.cacheDir.absolutePath

    Surface(
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { onPathClick("/") }) {
                Icon(Icons.Default.Home, contentDescription = null, modifier = Modifier.size(20.dp))
            }
            parts.forEachIndexed { index, part ->
                Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, modifier = Modifier.size(16.dp))
                TextButton(onClick = { onPathClick("/" + parts.take(index + 1).joinToString("/")) }) {
                    Text(part)
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FileListItem(
    file: RemoteFile,
    onClick: () -> Unit,
    onLongClick: () -> Unit,
    onRename: () -> Unit,
    onDelete: () -> Unit
) {
    var showMenu by remember { mutableStateOf(false) }

    ListItem(
        headlineContent = { Text(file.name, maxLines = 1) },
        supportingContent = {
            Text(
                "${formatFileSize(file.size)} • ${formatDate(file.lastModified)}",
                style = MaterialTheme.typography.bodySmall
            )
        },
        leadingContent = {
            Icon(
                imageVector = if (file.isDirectory) Icons.Default.Folder else getFileIcon(file.extension),
                contentDescription = null,
                tint = if (file.isDirectory) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
        },
        trailingContent = {
            Box {
                IconButton(onClick = { showMenu = true }) {
                    Icon(Icons.Default.MoreVert, contentDescription = null)
                }
                DropdownMenu(
                    expanded = showMenu,
                    onDismissRequest = { showMenu = false }
                ) {
                    DropdownMenuItem(
                        text = { Text(stringResource(R.string.rename)) },
                        onClick = { showMenu = false; onRename() },
                        leadingIcon = { Icon(Icons.Default.Edit, null) }
                    )
                    DropdownMenuItem(
                        text = { Text(stringResource(R.string.delete)) },
                        onClick = { showMenu = false; onDelete() },
                        leadingIcon = { Icon(Icons.Default.Delete, null) }
                    )
                }
            }
        },
        modifier = Modifier.combinedClickable(
            onClick = onClick,
            onLongClick = onLongClick
        )
    )
}

@Composable
fun getFileIcon(extension: String): androidx.compose.ui.graphics.vector.ImageVector {
    return when (extension.lowercase()) {
        "jpg", "jpeg", "png", "gif", "webp", "bmp" -> Icons.Default.Image
        "mp4", "avi", "mkv", "mov", "3gp" -> Icons.Default.VideoFile
        "mp3", "wav", "ogg", "flac" -> Icons.Default.AudioFile
        "pdf" -> Icons.Default.PictureAsPdf
        "txt", "log", "json", "xml", "html", "css", "js", "kt", "java", "md" -> Icons.Default.Description
        "zip", "tar", "gz", "rar", "7z" -> Icons.Default.Archive
        else -> Icons.Default.InsertDriveFile
    }
}

@Composable
fun NewFolderDialog(onDismiss: () -> Unit, onConfirm: (String) -> Unit) {
    var name by remember { mutableStateOf("") }
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(stringResource(R.string.new_folder)) },
        text = {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text(stringResource(R.string.folder_name)) },
                singleLine = true
            )
        },
        confirmButton = {
            TextButton(onClick = { if (name.isNotBlank()) onConfirm(name) }) {
                Text(stringResource(R.string.create))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text(stringResource(R.string.cancel)) }
        }
    )
}

@Composable
fun RenameDialog(oldName: String, onDismiss: () -> Unit, onConfirm: (String) -> Unit) {
    var name by remember { mutableStateOf(oldName) }
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(stringResource(R.string.rename)) },
        text = {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text(stringResource(R.string.new_name)) },
                singleLine = true
            )
        },
        confirmButton = {
            TextButton(onClick = { if (name.isNotBlank()) onConfirm(name) }) {
                Text(stringResource(R.string.confirm))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text(stringResource(R.string.cancel)) }
        }
    )
}

@Composable
fun DeleteConfirmDialog(fileName: String, onDismiss: () -> Unit, onConfirm: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(stringResource(R.string.delete)) },
        text = { Text(stringResource(R.string.delete_file_confirm, fileName)) },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text(stringResource(R.string.delete), color = MaterialTheme.colorScheme.error)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text(stringResource(R.string.cancel)) }
        }
    )
}

fun formatFileSize(size: Long): String {
    if (size < 1024) return "$size B"
    val kb = size / 1024.0
    if (kb < 1024) return String.format("%.1f KB", kb)
    val mb = kb / 1024
    if (mb < 1024) return String.format("%.1f MB", mb)
    return String.format("%.1f GB", mb / 1024)
}

fun formatDate(timestamp: Long): String {
    return SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(Date(timestamp))
}
