package com.ftplink.ui.screens;

import androidx.lifecycle.ViewModel;
import com.ftplink.data.repository.ServerRepository;
import com.ftplink.domain.model.ServerConfig;
import com.ftplink.domain.model.ServerType;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.StateFlow;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0012J\u000e\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u0012J\u000e\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u0012J\u000e\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\u0012J\u000e\u0010\u0019\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\u0012R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u001b"}, d2 = {"Lcom/ftplink/ui/screens/AddServerViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/ftplink/data/repository/ServerRepository;", "(Lcom/ftplink/data/repository/ServerRepository;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/ftplink/ui/screens/AddServerUiState;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "saveServer", "", "type", "Lcom/ftplink/domain/model/ServerType;", "updateHost", "host", "", "updateName", "name", "updatePassword", "password", "updatePort", "port", "updateUsername", "username", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class AddServerViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.ftplink.data.repository.ServerRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.ftplink.ui.screens.AddServerUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.ftplink.ui.screens.AddServerUiState> uiState = null;
    
    @javax.inject.Inject()
    public AddServerViewModel(@org.jetbrains.annotations.NotNull()
    com.ftplink.data.repository.ServerRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.ftplink.ui.screens.AddServerUiState> getUiState() {
        return null;
    }
    
    public final void updateName(@org.jetbrains.annotations.NotNull()
    java.lang.String name) {
    }
    
    public final void updateHost(@org.jetbrains.annotations.NotNull()
    java.lang.String host) {
    }
    
    public final void updatePort(@org.jetbrains.annotations.NotNull()
    java.lang.String port) {
    }
    
    public final void updateUsername(@org.jetbrains.annotations.NotNull()
    java.lang.String username) {
    }
    
    public final void updatePassword(@org.jetbrains.annotations.NotNull()
    java.lang.String password) {
    }
    
    public final void saveServer(@org.jetbrains.annotations.NotNull()
    com.ftplink.domain.model.ServerType type) {
    }
}