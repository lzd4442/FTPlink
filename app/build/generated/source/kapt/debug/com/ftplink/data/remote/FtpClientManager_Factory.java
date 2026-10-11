package com.ftplink.data.remote;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class FtpClientManager_Factory implements Factory<FtpClientManager> {
  @Override
  public FtpClientManager get() {
    return newInstance();
  }

  public static FtpClientManager_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static FtpClientManager newInstance() {
    return new FtpClientManager();
  }

  private static final class InstanceHolder {
    private static final FtpClientManager_Factory INSTANCE = new FtpClientManager_Factory();
  }
}
