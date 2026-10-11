package com.ftplink.ui.screens;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.core.content.FileProvider;
import androidx.lifecycle.ViewModel;
import com.ftplink.data.repository.ServerRepository;
import com.ftplink.domain.model.RemoteFile;
import com.ftplink.domain.model.ServerConfig;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.StateFlow;
import java.io.File;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\fJ\u000e\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0016J\u0006\u0010\u0017\u001a\u00020\u0012J\u0016\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0015\u001a\u00020\u0016J\u0010\u0010\u001b\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\fH\u0002J\u0006\u0010\u001d\u001a\u00020\u001eJ\u0010\u0010\u001f\u001a\u00020\u00122\u0006\u0010 \u001a\u00020\fH\u0002J\u000e\u0010!\u001a\u00020\u00122\u0006\u0010\"\u001a\u00020#J\u000e\u0010$\u001a\u00020\u00122\u0006\u0010 \u001a\u00020\fJ\b\u0010%\u001a\u00020\u0012H\u0014J\u0006\u0010&\u001a\u00020\u0012J\u0016\u0010\'\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010(\u001a\u00020\fR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00070\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010\u00a8\u0006)"}, d2 = {"Lcom/ftplink/ui/screens/FileBrowserViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/ftplink/data/repository/ServerRepository;", "(Lcom/ftplink/data/repository/ServerRepository;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/ftplink/ui/screens/FileBrowserUiState;", "currentServer", "Lcom/ftplink/domain/model/ServerConfig;", "pathStack", "", "", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "createFolder", "", "name", "delete", "file", "Lcom/ftplink/domain/model/RemoteFile;", "disconnect", "downloadAndOpen", "context", "Landroid/content/Context;", "getMimeType", "extension", "goBack", "", "loadFiles", "path", "loadServer", "serverId", "", "navigateTo", "onCleared", "refresh", "rename", "newName", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class FileBrowserViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.ftplink.data.repository.ServerRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.ftplink.ui.screens.FileBrowserUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.ftplink.ui.screens.FileBrowserUiState> uiState = null;
    @org.jetbrains.annotations.Nullable()
    private com.ftplink.domain.model.ServerConfig currentServer;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> pathStack = null;
    
    @javax.inject.Inject()
    public FileBrowserViewModel(@org.jetbrains.annotations.NotNull()
    com.ftplink.data.repository.ServerRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.ftplink.ui.screens.FileBrowserUiState> getUiState() {
        return null;
    }
    
    public final void loadServer(long serverId) {
    }
    
    public final void refresh() {
    }
    
    public final void navigateTo(@org.jetbrains.annotations.NotNull()
    java.lang.String path) {
    }
    
    public final boolean goBack() {
        return false;
    }
    
    private final void loadFiles(java.lang.String path) {
    }
    
    public final void createFolder(@org.jetbrains.annotations.NotNull()
    java.lang.String name) {
    }
    
    public final void rename(@org.jetbrains.annotations.NotNull()
    com.ftplink.domain.model.RemoteFile file, @org.jetbrains.annotations.NotNull()
    java.lang.String newName) {
    }
    
    public final void delete(@org.jetbrains.annotations.NotNull()
    com.ftplink.domain.model.RemoteFile file) {
    }
    
    public final void downloadAndOpen(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.ftplink.domain.model.RemoteFile file) {
    }
    
    private final java.lang.String getMimeType(java.lang.String extension) {
        return null;
    }
    
    public final void disconnect() {
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
}