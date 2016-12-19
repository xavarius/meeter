package com.maciejmalak.meeter.view.activities;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import com.maciejmalak.meeter.AndroidApplication;
import com.maciejmalak.meeter.R;
import com.maciejmalak.meeter.di.components.ApplicationComponent;
import com.maciejmalak.meeter.di.modules.ActivityModule;
import com.maciejmalak.meeter.navigation.Navigator;
import javax.inject.Inject;

public abstract class BaseActivity extends AppCompatActivity {
  @Inject Navigator mNavigator;
  @Inject ConnectivityManager mConnectivityManager;

  private ActivityModule mActivityModule;
  private AlertDialog mNoInternetDialog;

  public abstract void setupView();

  public abstract void initializeInjector();

  public abstract void injectViewToPresenter();

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setupView();
    initializeInjector();
    injectViewToPresenter();

    checkInternetConnection();
  }

  @Override protected void onResume() {
    super.onResume();
    checkInternetConnection();
  }

  @Override protected void onPause() {
    super.onPause();
    dismissNetDialog();
  }

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

  private void checkInternetConnection() {
    final NetworkInfo networkInfo = mConnectivityManager.getActiveNetworkInfo();
    if (networkInfo == null || !networkInfo.isConnected()) {
      buildNoInternetDialog();
      showNetDialog();
    }
  }

  private void buildNoInternetDialog() {
    if (mNoInternetDialog == null) {
      mNoInternetDialog = new AlertDialog.Builder(this).setCancelable(false)
          .setTitle(android.R.string.dialog_alert_title)
          .setMessage(R.string.no_internet_connection_msg)
          .setNeutralButton(android.R.string.cancel, (dialog, i) -> dialog.dismiss())
          .setPositiveButton(R.string.action_on,
              (dialog, i) -> mNavigator.navigateToWiFiSettings(this))
          .create();
    }
  }

  private void showNetDialog() {
    if (mNoInternetDialog != null && !mNoInternetDialog.isShowing()) {
      mNoInternetDialog.show();
    }
  }

  private void dismissNetDialog() {
    if (mNoInternetDialog != null && mNoInternetDialog.isShowing()) {
      mNoInternetDialog.dismiss();
      mNoInternetDialog = null;
    }
  }
}
