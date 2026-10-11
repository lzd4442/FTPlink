package com.ftplink.ui.screens;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.compose.foundation.ExperimentalFoundationApi;
import androidx.compose.foundation.layout.*;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.*;
import androidx.compose.material3.*;
import androidx.compose.runtime.*;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import com.ftplink.R;
import com.ftplink.domain.model.RemoteFile;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000>\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a,\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0007\u001a(\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u000b\u001a\u00020\fH\u0007\u001aH\u0010\r\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0007\u001a*\u0010\u0014\u001a\u00020\u00012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0015H\u0007\u001a$\u0010\u0016\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u00032\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0015H\u0007\u001a2\u0010\u0019\u001a\u00020\u00012\u0006\u0010\u001a\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0015H\u0007\u001a\u000e\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\t\u001a\u000e\u0010\u001d\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\t\u001a\u0010\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0003H\u0007\u00a8\u0006\""}, d2 = {"DeleteConfirmDialog", "", "fileName", "", "onDismiss", "Lkotlin/Function0;", "onConfirm", "FileBrowserScreen", "serverId", "", "onNavigateBack", "viewModel", "Lcom/ftplink/ui/screens/FileBrowserViewModel;", "FileListItem", "file", "Lcom/ftplink/domain/model/RemoteFile;", "onClick", "onLongClick", "onRename", "onDelete", "NewFolderDialog", "Lkotlin/Function1;", "PathBar", "path", "onPathClick", "RenameDialog", "oldName", "formatDate", "timestamp", "formatFileSize", "size", "getFileIcon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "extension", "app_debug"})
public final class FileBrowserScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void FileBrowserScreen(long serverId, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onNavigateBack, @org.jetbrains.annotations.NotNull()
    com.ftplink.ui.screens.FileBrowserViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void PathBar(@org.jetbrains.annotations.NotNull()
    java.lang.String path, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onPathClick) {
    }
    
    @kotlin.OptIn(markerClass = {androidx.compose.foundation.ExperimentalFoundationApi.class})
    @androidx.compose.runtime.Composable()
    public static final void FileListItem(@org.jetbrains.annotations.NotNull()
    com.ftplink.domain.model.RemoteFile file, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onClick, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onLongClick, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onRename, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDelete) {
    }
    
    @androidx.compose.runtime.Composable()
    @org.jetbrains.annotations.NotNull()
    public static final androidx.compose.ui.graphics.vector.ImageVector getFileIcon(@org.jetbrains.annotations.NotNull()
    java.lang.String extension) {
        return null;
    }
    
    @androidx.compose.runtime.Composable()
    public static final void NewFolderDialog(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onConfirm) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void RenameDialog(@org.jetbrains.annotations.NotNull()
    java.lang.String oldName, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onConfirm) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void DeleteConfirmDialog(@org.jetbrains.annotations.NotNull()
    java.lang.String fileName, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onConfirm) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String formatFileSize(long size) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String formatDate(long timestamp) {
        return null;
    }
}