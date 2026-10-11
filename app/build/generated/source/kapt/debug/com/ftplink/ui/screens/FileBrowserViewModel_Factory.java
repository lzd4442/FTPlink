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
public final class FileBrowserViewModel_Factory implements Factory<FileBrowserViewModel> {
  private final Provider<ServerRepository> repositoryProvider;

  public FileBrowserViewModel_Factory(Provider<ServerRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public FileBrowserViewModel get() {
    return newInstance(repositoryProvider.get());
  }

  public static FileBrowserViewModel_Factory create(Provider<ServerRepository> repositoryProvider) {
    return new FileBrowserViewModel_Factory(repositoryProvider);
  }

  public static FileBrowserViewModel newInstance(ServerRepository repository) {
    return new FileBrowserViewModel(repository);
  }
}
