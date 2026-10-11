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
public final class AddServerViewModel_Factory implements Factory<AddServerViewModel> {
  private final Provider<ServerRepository> repositoryProvider;

  public AddServerViewModel_Factory(Provider<ServerRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public AddServerViewModel get() {
    return newInstance(repositoryProvider.get());
  }

  public static AddServerViewModel_Factory create(Provider<ServerRepository> repositoryProvider) {
    return new AddServerViewModel_Factory(repositoryProvider);
  }

  public static AddServerViewModel newInstance(ServerRepository repository) {
    return new AddServerViewModel(repository);
  }
}
