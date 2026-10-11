package com.ftplink.di;

import com.ftplink.data.local.FTPlinkDatabase;
import com.ftplink.data.local.SettingDao;
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
public final class AppModule_ProvideSettingDaoFactory implements Factory<SettingDao> {
  private final Provider<FTPlinkDatabase> databaseProvider;

  public AppModule_ProvideSettingDaoFactory(Provider<FTPlinkDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public SettingDao get() {
    return provideSettingDao(databaseProvider.get());
  }

  public static AppModule_ProvideSettingDaoFactory create(
      Provider<FTPlinkDatabase> databaseProvider) {
    return new AppModule_ProvideSettingDaoFactory(databaseProvider);
  }

  public static SettingDao provideSettingDao(FTPlinkDatabase database) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideSettingDao(database));
  }
}
