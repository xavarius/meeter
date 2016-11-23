package com.maciejmalak.meeter;

import android.app.Application;
import com.maciejmalak.meeter.di.components.ApplicationComponent;
import com.maciejmalak.meeter.di.components.DaggerApplicationComponent;
import com.maciejmalak.meeter.di.modules.ApplicationModule;
import com.squareup.leakcanary.LeakCanary;
import org.jetbrains.annotations.NotNull;

public class AndroidApplication extends Application {
  private ApplicationComponent mApplicationComponent;

  @Override public void onCreate() {
    super.onCreate();
    initializeInjector();
    initializeLeakCanary();
  }

  private void initializeInjector() {
    mApplicationComponent =
        DaggerApplicationComponent.builder()
            .applicationModule(new ApplicationModule(this))
            .build();
  }

  private void initializeLeakCanary() {
    if (!LeakCanary.isInAnalyzerProcess(this) && BuildConfig.DEBUG) LeakCanary.install(this);
  }

  @NotNull public ApplicationComponent getApplicationComponent() {
    return mApplicationComponent;
  }
}
