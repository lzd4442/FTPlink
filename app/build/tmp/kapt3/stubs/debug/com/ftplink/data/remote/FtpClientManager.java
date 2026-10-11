package com.ftplink.data.remote;

import com.ftplink.domain.model.RemoteFile;
import com.jcraft.jsch.*;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import java.io.InputStream;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002JD\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u0004H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u001a\u0010\u001bJ(\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0004H\u0002J(\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0004H\u0002J$\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u0010\u001f\u001a\u00020\u0004H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b \u0010!J,\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u0010\u001f\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\nH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b$\u0010%J\u0006\u0010&\u001a\u00020\u0013J,\u0010\'\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u0010(\u001a\u00020\u00042\u0006\u0010)\u001a\u00020\u0004H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b*\u0010+J$\u0010,\u001a\b\u0012\u0004\u0012\u00020-0\u00122\u0006\u0010(\u001a\u00020\u0004H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b.\u0010!J*\u0010/\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u000201000\u00122\u0006\u0010\u001f\u001a\u00020\u0004H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b2\u0010!J\u0016\u00103\u001a\b\u0012\u0004\u0012\u000201002\u0006\u0010\u001f\u001a\u00020\u0004H\u0002J\u0016\u00104\u001a\b\u0012\u0004\u0012\u000201002\u0006\u0010\u001f\u001a\u00020\u0004H\u0002J,\u00105\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u00106\u001a\u00020\u00042\u0006\u00107\u001a\u00020\u0004H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b8\u0010+J\u0014\u00109\u001a\u000201*\u00020:2\u0006\u0010;\u001a\u00020\u0004H\u0002J\u0014\u00109\u001a\u000201*\u00020<2\u0006\u0010;\u001a\u00020\u0004H\u0002R\u001e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u0086\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\n@BX\u0086\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006="}, d2 = {"Lcom/ftplink/data/remote/FtpClientManager;", "", "()V", "<set-?>", "", "currentServerType", "getCurrentServerType", "()Ljava/lang/String;", "ftpClient", "Lorg/apache/commons/net/ftp/FTPClient;", "", "isConnected", "()Z", "jsch", "Lcom/jcraft/jsch/JSch;", "sftpChannel", "Lcom/jcraft/jsch/ChannelSftp;", "connect", "Lkotlin/Result;", "", "host", "port", "", "username", "password", "type", "connect-hUnOzRk", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "connectFtp", "connectSftp", "createDirectory", "path", "createDirectory-gIAlu-s", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "delete", "isDirectory", "delete-0E7RQCE", "(Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "disconnect", "downloadFile", "remotePath", "localPath", "downloadFile-0E7RQCE", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getInputStream", "Ljava/io/InputStream;", "getInputStream-gIAlu-s", "listFiles", "", "Lcom/ftplink/domain/model/RemoteFile;", "listFiles-gIAlu-s", "listFtpFiles", "listSftpFiles", "rename", "oldPath", "newPath", "rename-0E7RQCE", "toRemoteFile", "Lcom/jcraft/jsch/ChannelSftp$LsEntry;", "parentPath", "Lorg/apache/commons/net/ftp/FTPFile;", "app_debug"})
public final class FtpClientManager {
    @org.jetbrains.annotations.Nullable()
    private org.apache.commons.net.ftp.FTPClient ftpClient;
    @org.jetbrains.annotations.Nullable()
    private com.jcraft.jsch.ChannelSftp sftpChannel;
    @org.jetbrains.annotations.Nullable()
    private com.jcraft.jsch.JSch jsch;
    private boolean isConnected = false;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String currentServerType = "FTP";
    
    @javax.inject.Inject()
    public FtpClientManager() {
        super();
    }
    
    public final boolean isConnected() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCurrentServerType() {
        return null;
    }
    
    private final void connectFtp(java.lang.String host, int port, java.lang.String username, java.lang.String password) {
    }
    
    private final void connectSftp(java.lang.String host, int port, java.lang.String username, java.lang.String password) {
    }
    
    private final java.util.List<com.ftplink.domain.model.RemoteFile> listFtpFiles(java.lang.String path) {
        return null;
    }
    
    private final java.util.List<com.ftplink.domain.model.RemoteFile> listSftpFiles(java.lang.String path) {
        return null;
    }
    
    public final void disconnect() {
    }
    
    private final com.ftplink.domain.model.RemoteFile toRemoteFile(org.apache.commons.net.ftp.FTPFile $this$toRemoteFile, java.lang.String parentPath) {
        return null;
    }
    
    private final com.ftplink.domain.model.RemoteFile toRemoteFile(com.jcraft.jsch.ChannelSftp.LsEntry $this$toRemoteFile, java.lang.String parentPath) {
        return null;
    }
}