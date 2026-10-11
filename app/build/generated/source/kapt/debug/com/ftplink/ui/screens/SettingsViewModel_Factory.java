package com.ftplink.ui.screens;

import android.content.Context;
import com.ftplink.data.local.SettingDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class SettingsViewModel_Factory implements Factory<SettingsViewModel> {
  private final Provider<SettingDao> settingDaoProvider;

  private final Provider<Context> contextProvider;

  public SettingsViewModel_Factory(Provider<SettingDao> settingDaoProvider,
      Provider<Context> contextProvider) {
    this.settingDaoProvider = settingDaoProvider;
    this.contextProvider = contextProvider;
  }

  @Override
  public SettingsViewModel get() {
    return newInstance(settingDaoProvider.get(), contextProvider.get());
  }

  public static SettingsViewModel_Factory create(Provider<SettingDao> settingDaoProvider,
      Provider<Context> contextProvider) {
    return new SettingsViewModel_Factory(settingDaoProvider, contextProvider);
  }

  public static SettingsViewModel newInstance(SettingDao settingDao, Context context) {
    return new SettingsViewModel(settingDao, context);
  }
}
