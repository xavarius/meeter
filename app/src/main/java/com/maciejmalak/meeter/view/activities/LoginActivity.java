package com.maciejmalak.meeter.view.activities;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;
import com.maciejmalak.meeter.R;
import com.maciejmalak.meeter.di.components.DaggerLoginComponent;
import com.maciejmalak.meeter.di.components.LoginComponent;
import com.maciejmalak.meeter.presenters.LoginPresenter;
import com.maciejmalak.meeter.view.LoginView;
import javax.inject.Inject;
import org.jetbrains.annotations.NotNull;

public class LoginActivity extends BaseActivity implements LoginView {
  @Inject LoginPresenter mPresenter;
  @Inject GoogleApiClient mGoogleSignClient;

  public static Intent getCallingIntent(@NotNull Context context) {
    return new Intent(context, LoginActivity.class);
  }

  @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    mPresenter.onActivityResulted(requestCode, (resultCode == RESULT_OK) , data);
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    mPresenter = null;
    mGoogleSignClient = null;
  }

  @Override public void setupView() {
    setContentView(R.layout.activity_login);
    ButterKnife.bind(this);
  }

  @Override public void initializeInjector() {
    final LoginComponent component = DaggerLoginComponent.builder()
        .applicationComponent(getApplicationComponent())
        .activityModule(getActivityModule())
        .build();
        component.inject(this);
  }

  @Override public void injectViewToPresenter() {
    mPresenter.setView(this);
  }

  @Override public void launchHomeActivity() {
    mNavigator.navigateToHome(this);
  }

  @Override public void launchGoogleSignIn(int googleSignResult) {
    final Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleSignClient);
    startActivityForResult(signInIntent, googleSignResult);
  }

  @OnClick(R.id.google_sign_in_button) void onGoogleSignInClicked() {
    mPresenter.onGoogleSignBtnClicked();
  }

  @Override public void showErrLoginMessage() {
    showShortError(R.string.sign_in_error);
  }

  @Override public void showShortError(int id) {
    final String err = getString(id);
    Toast.makeText(this, err, Toast.LENGTH_SHORT).show();
  }

  @Override public void showLongError(int id) {
    final String err = getString(id);
    Toast.makeText(this, err, Toast.LENGTH_LONG).show();
  }
}
