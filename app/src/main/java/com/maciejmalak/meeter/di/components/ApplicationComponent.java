package com.maciejmalak.meeter.di.components;

import android.content.Context;
import com.maciejmalak.meeter.di.modules.ApplicationModule;
import com.maciejmalak.meeter.di.scopes.PerApp;
import com.maciejmalak.meeter.domain.executor.ThreadExecutor;
import com.maciejmalak.meeter.navigation.Navigator;
import dagger.Component;

@PerApp
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
  Context context();
  Navigator navigator();
  ThreadExecutor threadExecutor();
}
