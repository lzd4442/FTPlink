package com.ftplink.data.repository;

import com.ftplink.data.local.ServerDao;
import com.ftplink.data.local.ServerEntity;
import com.ftplink.data.remote.FtpClientManager;
import com.ftplink.domain.model.RemoteFile;
import com.ftplink.domain.model.ServerConfig;
import com.ftplink.domain.model.ServerType;
import kotlinx.coroutines.flow.Flow;
import java.io.InputStream;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J$\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u000f\u0010\u0010J$\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\u0012\u001a\u00020\u0013H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0014\u0010\u0015J,\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\bH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u0016\u0010\u001a\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u001cH\u0086@\u00a2\u0006\u0002\u0010\u001dJ\u0006\u0010\u001e\u001a\u00020\fJ,\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010 \u001a\u00020\u00132\u0006\u0010!\u001a\u00020\u0013H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\"\u0010#J\u0012\u0010$\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0&0%J$\u0010\'\u001a\b\u0012\u0004\u0012\u00020(0\u000b2\u0006\u0010 \u001a\u00020\u0013H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b)\u0010\u0015J\u0018\u0010*\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u001b\u001a\u00020\u001cH\u0086@\u00a2\u0006\u0002\u0010\u001dJ*\u0010+\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020,0&0\u000b2\u0006\u0010\u0012\u001a\u00020\u0013H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b-\u0010\u0015J,\u0010.\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010/\u001a\u00020\u00132\u0006\u00100\u001a\u00020\u0013H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b1\u0010#J\u0016\u00102\u001a\u00020\u001c2\u0006\u0010\r\u001a\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010\u0010J\u0016\u00103\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010\u0010J\f\u00104\u001a\u000205*\u00020\u000eH\u0002J\f\u00106\u001a\u00020\u000e*\u000205H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\b8F\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u00067"}, d2 = {"Lcom/ftplink/data/repository/ServerRepository;", "", "serverDao", "Lcom/ftplink/data/local/ServerDao;", "ftpClientManager", "Lcom/ftplink/data/remote/FtpClientManager;", "(Lcom/ftplink/data/local/ServerDao;Lcom/ftplink/data/remote/FtpClientManager;)V", "isConnected", "", "()Z", "connect", "Lkotlin/Result;", "", "server", "Lcom/ftplink/domain/model/ServerConfig;", "connect-gIAlu-s", "(Lcom/ftplink/domain/model/ServerConfig;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createDirectory", "path", "", "createDirectory-gIAlu-s", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "delete", "isDirectory", "delete-0E7RQCE", "(Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteServer", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "disconnect", "downloadFile", "remotePath", "localPath", "downloadFile-0E7RQCE", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllServers", "Lkotlinx/coroutines/flow/Flow;", "", "getInputStream", "Ljava/io/InputStream;", "getInputStream-gIAlu-s", "getServerById", "listFiles", "Lcom/ftplink/domain/model/RemoteFile;", "listFiles-gIAlu-s", "rename", "oldPath", "newPath", "rename-0E7RQCE", "saveServer", "updateServer", "toEntity", "Lcom/ftplink/data/local/ServerEntity;", "toServerConfig", "app_debug"})
public final class ServerRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.ftplink.data.local.ServerDao serverDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.ftplink.data.remote.FtpClientManager ftpClientManager = null;
    
    @javax.inject.Inject()
    public ServerRepository(@org.jetbrains.annotations.NotNull()
    com.ftplink.data.local.ServerDao serverDao, @org.jetbrains.annotations.NotNull()
    com.ftplink.data.remote.FtpClientManager ftpClientManager) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.ftplink.domain.model.ServerConfig>> getAllServers() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getServerById(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.ftplink.domain.model.ServerConfig> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object saveServer(@org.jetbrains.annotations.NotNull()
    com.ftplink.domain.model.ServerConfig server, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateServer(@org.jetbrains.annotations.NotNull()
    com.ftplink.domain.model.ServerConfig server, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteServer(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    public final void disconnect() {
    }
    
    public final boolean isConnected() {
        return false;
    }
    
    private final com.ftplink.domain.model.ServerConfig toServerConfig(com.ftplink.data.local.ServerEntity $this$toServerConfig) {
        return null;
    }
    
    private final com.ftplink.data.local.ServerEntity toEntity(com.ftplink.domain.model.ServerConfig $this$toEntity) {
        return null;
    }
}