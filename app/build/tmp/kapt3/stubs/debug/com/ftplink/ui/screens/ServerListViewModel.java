package com.ftplink.ui.screens;

import androidx.lifecycle.ViewModel;
import com.ftplink.data.repository.ServerRepository;
import com.ftplink.domain.model.ServerConfig;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.StateFlow;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010J\u0006\u0010\u0011\u001a\u00020\u000eR\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\u0012"}, d2 = {"Lcom/ftplink/ui/screens/ServerListViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/ftplink/data/repository/ServerRepository;", "(Lcom/ftplink/data/repository/ServerRepository;)V", "_servers", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lcom/ftplink/domain/model/ServerConfig;", "servers", "Lkotlinx/coroutines/flow/StateFlow;", "getServers", "()Lkotlinx/coroutines/flow/StateFlow;", "deleteServer", "", "id", "", "loadServers", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class ServerListViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.ftplink.data.repository.ServerRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.ftplink.domain.model.ServerConfig>> _servers = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.ftplink.domain.model.ServerConfig>> servers = null;
    
    @javax.inject.Inject()
    public ServerListViewModel(@org.jetbrains.annotations.NotNull()
    com.ftplink.data.repository.ServerRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.ftplink.domain.model.ServerConfig>> getServers() {
        return null;
    }
    
    public final void loadServers() {
    }
    
    public final void deleteServer(long id) {
    }
}