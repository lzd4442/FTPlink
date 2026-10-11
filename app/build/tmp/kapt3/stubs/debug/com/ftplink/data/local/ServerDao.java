package com.ftplink.data.local;

import androidx.room.*;
import kotlinx.coroutines.flow.Flow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u0014\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\r0\fH\'J\u0018\u0010\u000e\u001a\u0004\u0018\u00010\u00052\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u0016\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lcom/ftplink/data/local/ServerDao;", "", "deleteServer", "", "server", "Lcom/ftplink/data/local/ServerEntity;", "(Lcom/ftplink/data/local/ServerEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteServerById", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllServers", "Lkotlinx/coroutines/flow/Flow;", "", "getServerById", "insertServer", "updateServer", "app_debug"})
@androidx.room.Dao()
public abstract interface ServerDao {
    
    @androidx.room.Query(value = "SELECT * FROM servers ORDER BY createdAt DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.ftplink.data.local.ServerEntity>> getAllServers();
    
    @androidx.room.Query(value = "SELECT * FROM servers WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getServerById(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.ftplink.data.local.ServerEntity> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertServer(@org.jetbrains.annotations.NotNull()
    com.ftplink.data.local.ServerEntity server, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateServer(@org.jetbrains.annotations.NotNull()
    com.ftplink.data.local.ServerEntity server, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteServer(@org.jetbrains.annotations.NotNull()
    com.ftplink.data.local.ServerEntity server, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM servers WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteServerById(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}