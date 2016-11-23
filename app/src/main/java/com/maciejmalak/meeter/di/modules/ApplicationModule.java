package com.maciejmalak.meeter.di.modules;

import android.content.Context;
import com.maciejmalak.meeter.AndroidApplication;
import com.maciejmalak.meeter.di.scopes.PerApp;
import com.maciejmalak.meeter.domain.executor.ThreadExecutor;
import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
  private final AndroidApplication mApplication;

  public ApplicationModule(final AndroidApplication app) { this.mApplication = app; }

  @Provides @PerApp Context provideApplicationContext() { return this.mApplication; }

  @Provides @PerApp ThreadExecutor provideThreadExecutor(ThreadExecutor threadExecutor) { return threadExecutor; }
}
