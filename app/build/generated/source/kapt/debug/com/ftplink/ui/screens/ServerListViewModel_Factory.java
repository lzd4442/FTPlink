package com.ftplink.ui.screens;

import com.ftplink.data.repository.ServerRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class ServerListViewModel_Factory implements Factory<ServerListViewModel> {
  private final Provider<ServerRepository> repositoryProvider;

  public ServerListViewModel_Factory(Provider<ServerRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public ServerListViewModel get() {
    return newInstance(repositoryProvider.get());
  }

  public static ServerListViewModel_Factory create(Provider<ServerRepository> repositoryProvider) {
    return new ServerListViewModel_Factory(repositoryProvider);
  }

  public static ServerListViewModel newInstance(ServerRepository repository) {
    return new ServerListViewModel(repository);
  }
}
