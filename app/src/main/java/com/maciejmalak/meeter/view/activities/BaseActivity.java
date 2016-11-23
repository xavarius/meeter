package com.maciejmalak.meeter.view.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.maciejmalak.meeter.AndroidApplication;
import com.maciejmalak.meeter.di.components.ApplicationComponent;
import com.maciejmalak.meeter.di.modules.ActivityModule;
import com.maciejmalak.meeter.navigation.Navigator;
import javax.inject.Inject;

public class BaseActivity extends AppCompatActivity {
  @Inject Navigator mNavigator;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  protected ApplicationComponent getApplicationComponent() {
    return ((AndroidApplication) getApplication()).getApplicationComponent();
  }

  protected ActivityModule getActivityModule() {
    return new ActivityModule(this);
  }
}