package com.maciejmalak.meeter.di.components;

import com.maciejmalak.meeter.di.modules.ActivityModule;
import com.maciejmalak.meeter.di.modules.LoginModule;
import com.maciejmalak.meeter.di.scopes.PerActivity;
import com.maciejmalak.meeter.view.activities.LoginActivity;
import dagger.Component;

@PerActivity
@Component(dependencies = { ApplicationComponent.class },
modules = {
    ActivityModule.class, LoginModule.class
})
public interface LoginComponent extends ActivityComponent {
  void inject(LoginActivity loginActivity);
}