package com.ftplink.ui.screens;

import androidx.compose.foundation.layout.*;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.*;
import androidx.compose.material3.*;
import androidx.compose.runtime.*;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import com.ftplink.R;
import com.ftplink.domain.model.ServerConfig;
import com.ftplink.domain.model.ServerType;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000(\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a,\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0007\u001aB\u0010\u0007\u001a\u00020\u00012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00010\n2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\r\u001a\u00020\u000eH\u0007\u00a8\u0006\u000f"}, d2 = {"ServerCard", "", "server", "Lcom/ftplink/domain/model/ServerConfig;", "onClick", "Lkotlin/Function0;", "onDelete", "ServerListScreen", "onNavigateToAddServer", "onNavigateToFileBrowser", "Lkotlin/Function1;", "", "onNavigateToSettings", "viewModel", "Lcom/ftplink/ui/screens/ServerListViewModel;", "app_debug"})
public final class ServerListScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void ServerListScreen(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onNavigateToAddServer, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Long, kotlin.Unit> onNavigateToFileBrowser, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onNavigateToSettings, @org.jetbrains.annotations.NotNull()
    com.ftplink.ui.screens.ServerListViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void ServerCard(@org.jetbrains.annotations.NotNull()
    com.ftplink.domain.model.ServerConfig server, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onClick, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDelete) {
    }
}