package com.maciejmalak.meeter.view.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.maciejmalak.meeter.AndroidApplication;
import com.maciejmalak.meeter.di.components.ApplicationComponent;
import com.maciejmalak.meeter.di.modules.ActivityModule;
import com.maciejmalak.meeter.navigation.Navigator;
import javax.inject.Inject;

public abstract class BaseActivity extends AppCompatActivity {
  @Inject Navigator mNavigator;

  private ActivityModule mActivityModule;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setupView();
    initializeInjector();
    injectViewToPresenter();
  }

  protected abstract void setupView();
  protected abstract void initializeInjector();
  protected abstract void injectViewToPresenter();

  @Override protected void onDestroy() {
    super.onDestroy();
    mActivityModule = null;
    mNavigator = null;
  }

  protected ApplicationComponent getApplicationComponent() {
    return ((AndroidApplication) getApplication()).getApplicationComponent();
  }

  protected ActivityModule getActivityModule() {
    if (mActivityModule == null) mActivityModule = new ActivityModule(this);
    return mActivityModule;
  }
}
