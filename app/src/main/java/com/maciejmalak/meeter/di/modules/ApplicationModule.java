package com.maciejmalak.meeter.di.modules;

import android.content.Context;
import android.net.ConnectivityManager;
import com.google.firebase.auth.FirebaseAuth;
import com.maciejmalak.meeter.AndroidApplication;
import com.maciejmalak.meeter.di.scopes.PerApp;
import com.maciejmalak.meeter.domain.MainThread;
import com.maciejmalak.meeter.domain.executor.ThreadExecutor;
import com.maciejmalak.meeter.domain.threading.MainThreadImpl;
import dagger.Module;
import dagger.Provides;

/**
 * Dostarcza tylko obiekty typów, które powinny być dostępne przez cały lifetime aplikacji!
 * */

@Module
public class ApplicationModule {
  private final AndroidApplication mApplication;

  public ApplicationModule(final AndroidApplication app) { this.mApplication = app; }

  @Provides @PerApp Context provideApplicationContext() { return this.mApplication; }

  @Provides @PerApp ThreadExecutor provideThreadExecutor() { return new ThreadExecutor(); }

  @Provides @PerApp MainThread provideMainThread() { return MainThreadImpl.getInstance(); }

  @Provides @PerApp FirebaseAuth provideFirebaseAuth() { return FirebaseAuth.getInstance(); }

  @Provides @PerApp ConnectivityManager provideCM() {
    return (ConnectivityManager) mApplication.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
  }
}
