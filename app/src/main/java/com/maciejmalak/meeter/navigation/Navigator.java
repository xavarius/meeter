package com.maciejmalak.meeter.navigation;

import android.content.Context;
import android.content.Intent;
import com.maciejmalak.meeter.view.activities.LoginActivity;
import com.maciejmalak.meeter.view.activities.MainActivity;
import com.maciejmalak.meeter.di.scopes.PerApp;
import javax.inject.Inject;
import org.jetbrains.annotations.NotNull;

@PerApp
public class Navigator {

  @Inject Navigator() {}

  public void navigateToHome(@NotNull final Context context) {
    final Intent start = MainActivity.getCallingIntent(context);
    context.startActivity(start);
  }

  public void navigateToLogin(@NotNull final Context context) {
    final Intent start = LoginActivity.getCallingIntent(context);
    context.startActivity(start);
  }
}
