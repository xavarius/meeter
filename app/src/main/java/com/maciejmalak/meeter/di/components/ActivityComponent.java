package com.maciejmalak.meeter.di.components;

import com.maciejmalak.meeter.di.modules.ActivityModule;
import com.maciejmalak.meeter.di.scopes.PerActivity;
import com.maciejmalak.meeter.view.activities.BaseActivity;
import com.maciejmalak.meeter.view.activities.LoginActivity;
import com.maciejmalak.meeter.view.activities.MainActivity;
import dagger.Component;

@PerActivity
@Component(
    dependencies = ApplicationComponent.class,
    modules = {
        ActivityModule.class
    }
)
public interface ActivityComponent {
  void inject(final MainActivity mainActivity);
  void inject(final LoginActivity loginActivity);

  BaseActivity baseActivity();
}
