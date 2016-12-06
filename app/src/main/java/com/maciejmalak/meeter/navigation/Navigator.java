package com.maciejmalak.meeter.navigation;

import android.content.Context;
import android.content.Intent;
import com.maciejmalak.meeter.di.scopes.PerApp;
import com.maciejmalak.meeter.view.activities.LoginActivity;
import com.maciejmalak.meeter.view.activities.MainActivity;
import javax.inject.Inject;
import org.jetbrains.annotations.NotNull;

import static android.provider.Settings.ACTION_WIFI_SETTINGS;

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

  public void navigateToWiFiSettings(@NotNull final Context context) {
    context.startActivity(new Intent(ACTION_WIFI_SETTINGS));
  }
}
