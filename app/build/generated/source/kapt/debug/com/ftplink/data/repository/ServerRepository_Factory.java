package com.ftplink.data.repository;

import com.ftplink.data.local.ServerDao;
import com.ftplink.data.remote.FtpClientManager;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class ServerRepository_Factory implements Factory<ServerRepository> {
  private final Provider<ServerDao> serverDaoProvider;

  private final Provider<FtpClientManager> ftpClientManagerProvider;

  public ServerRepository_Factory(Provider<ServerDao> serverDaoProvider,
      Provider<FtpClientManager> ftpClientManagerProvider) {
    this.serverDaoProvider = serverDaoProvider;
    this.ftpClientManagerProvider = ftpClientManagerProvider;
  }

  @Override
  public ServerRepository get() {
    return newInstance(serverDaoProvider.get(), ftpClientManagerProvider.get());
  }

  public static ServerRepository_Factory create(Provider<ServerDao> serverDaoProvider,
      Provider<FtpClientManager> ftpClientManagerProvider) {
    return new ServerRepository_Factory(serverDaoProvider, ftpClientManagerProvider);
  }

  public static ServerRepository newInstance(ServerDao serverDao,
      FtpClientManager ftpClientManager) {
    return new ServerRepository(serverDao, ftpClientManager);
  }
}
