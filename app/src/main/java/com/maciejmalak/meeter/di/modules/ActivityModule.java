package com.maciejmalak.meeter.di.modules;

import com.maciejmalak.meeter.di.scopes.PerActivity;
import com.maciejmalak.meeter.view.activities.BaseActivity;
import dagger.Module;
import dagger.Provides;

@Module public class ActivityModule {
  private final BaseActivity mActivity;

  public ActivityModule(final BaseActivity activity) {
    mActivity = activity;
  }

  @Provides @PerActivity BaseActivity providesActivity() {
    return this.mActivity;
  }
}
