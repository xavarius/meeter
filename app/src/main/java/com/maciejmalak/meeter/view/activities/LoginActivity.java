package com.maciejmalak.meeter.view.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;
import com.maciejmalak.meeter.R;
import com.maciejmalak.meeter.di.components.ActivityComponent;
import com.maciejmalak.meeter.di.components.DaggerActivityComponent;
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

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setupViews();
    initializeInjector();
    mPresenter.setView(this);
  }

  @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    mPresenter.onActivityResulted(requestCode, (resultCode == RESULT_OK) , data);
  }

  private void setupViews() {
    setContentView(R.layout.activity_login);
    ButterKnife.bind(this);
  }

  private void initializeInjector() {
    ActivityComponent component = DaggerActivityComponent.builder()
        .applicationComponent(getApplicationComponent())
        .activityModule(getActivityModule())
        .build();
    component.inject(this);
  }

  @Override public void launchHomeActivity() {
    mNavigator.navigateToHome(this);
  }

  @Override public void launchGoogleSignIn(int googleSignResult) {
    final Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleSignClient);
    startActivityForResult(signInIntent, googleSignResult);
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

  @OnClick(R.id.google_sign_in_button) void onGoogleSignInClicked() {
    mPresenter.onGoogleSignBtnClicked();
  }
}
