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

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001Ba\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\t\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\f\u001a\u00020\t\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e\u00a2\u0006\u0002\u0010\u000fJ\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\tH\u00c6\u0003J\t\u0010\u001e\u001a\u00020\tH\u00c6\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010 \u001a\u00020\tH\u00c6\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u000eH\u00c6\u0003Je\u0010\"\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\f\u001a\u00020\t2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000eH\u00c6\u0001J\u0013\u0010#\u001a\u00020\t2\b\u0010$\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010%\u001a\u00020&H\u00d6\u0001J\t\u0010\'\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\f\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\n\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0013R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0013R\u0013\u0010\r\u001a\u0004\u0018\u00010\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0011\u00a8\u0006("}, d2 = {"Lcom/ftplink/ui/screens/FileBrowserUiState;", "", "serverName", "", "currentPath", "files", "", "Lcom/ftplink/domain/model/RemoteFile;", "isLoading", "", "isDownloading", "error", "disconnected", "openFileIntent", "Landroid/content/Intent;", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;ZZLjava/lang/String;ZLandroid/content/Intent;)V", "getCurrentPath", "()Ljava/lang/String;", "getDisconnected", "()Z", "getError", "getFiles", "()Ljava/util/List;", "getOpenFileIntent", "()Landroid/content/Intent;", "getServerName", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"})
public final class FileBrowserUiState {
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String serverName = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String currentPath = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.ftplink.domain.model.RemoteFile> files = null;
    private final boolean isLoading = false;
    private final boolean isDownloading = false;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String error = null;
    private final boolean disconnected = false;
    @org.jetbrains.annotations.Nullable()
    private final android.content.Intent openFileIntent = null;
    
    public FileBrowserUiState(@org.jetbrains.annotations.Nullable()
    java.lang.String serverName, @org.jetbrains.annotations.NotNull()
    java.lang.String currentPath, @org.jetbrains.annotations.NotNull()
    java.util.List<com.ftplink.domain.model.RemoteFile> files, boolean isLoading, boolean isDownloading, @org.jetbrains.annotations.Nullable()
    java.lang.String error, boolean disconnected, @org.jetbrains.annotations.Nullable()
    android.content.Intent openFileIntent) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getServerName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCurrentPath() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.ftplink.domain.model.RemoteFile> getFiles() {
        return null;
    }
    
    public final boolean isLoading() {
        return false;
    }
    
    public final boolean isDownloading() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getError() {
        return null;
    }
    
    public final boolean getDisconnected() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.content.Intent getOpenFileIntent() {
        return null;
    }
    
    public FileBrowserUiState() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.ftplink.domain.model.RemoteFile> component3() {
        return null;
    }
    
    public final boolean component4() {
        return false;
    }
    
    public final boolean component5() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component6() {
        return null;
    }
    
    public final boolean component7() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.content.Intent component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.ftplink.ui.screens.FileBrowserUiState copy(@org.jetbrains.annotations.Nullable()
    java.lang.String serverName, @org.jetbrains.annotations.NotNull()
    java.lang.String currentPath, @org.jetbrains.annotations.NotNull()
    java.util.List<com.ftplink.domain.model.RemoteFile> files, boolean isLoading, boolean isDownloading, @org.jetbrains.annotations.Nullable()
    java.lang.String error, boolean disconnected, @org.jetbrains.annotations.Nullable()
    android.content.Intent openFileIntent) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}