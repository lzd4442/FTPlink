package com.ftplink.di;

import com.ftplink.data.local.FTPlinkDatabase;
import com.ftplink.data.local.ServerDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class AppModule_ProvideServerDaoFactory implements Factory<ServerDao> {
  private final Provider<FTPlinkDatabase> databaseProvider;

  public AppModule_ProvideServerDaoFactory(Provider<FTPlinkDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public ServerDao get() {
    return provideServerDao(databaseProvider.get());
  }

  public static AppModule_ProvideServerDaoFactory create(
      Provider<FTPlinkDatabase> databaseProvider) {
    return new AppModule_ProvideServerDaoFactory(databaseProvider);
  }

  public static ServerDao provideServerDao(FTPlinkDatabase database) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideServerDao(database));
  }
}
