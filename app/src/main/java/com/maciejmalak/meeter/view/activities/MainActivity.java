package com.maciejmalak.meeter.view.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.maciejmalak.meeter.R;
import com.maciejmalak.meeter.di.components.ActivityComponent;
import com.maciejmalak.meeter.di.components.DaggerActivityComponent;
import com.maciejmalak.meeter.presenters.HomeMapPresenter;
import com.maciejmalak.meeter.view.HomeMapView;
import javax.inject.Inject;
import org.jetbrains.annotations.NotNull;

/*TODO remove every unnessesary reference when activity is stopped/destroyed/closed */
public class MainActivity extends BaseActivity implements HomeMapView {
  @Inject HomeMapPresenter mPresenter;

  @BindView(R.id.toolbar) Toolbar mToolbar;

  private ActivityComponent mComponent;

  public static Intent getCallingIntent(@NotNull Context context) {
    return new Intent(context, MainActivity.class);
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setupView();
    initializeInjector();
    mPresenter.setView(this); /*TODO Injecting view to Presenter by Dagger? */
  }

  private void setupView() {
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    setSupportActionBar(mToolbar);
  }

  private void initializeInjector() {
    mComponent = DaggerActivityComponent.builder()
        .applicationComponent(getApplicationComponent())
        .activityModule(getActivityModule())
        .build();
    mComponent.inject(this);
  }

  @OnClick(R.id.fab) void onFabClicked() {
    mPresenter.onFabButtonClicked();
  }

  @Override public void launchLoginActivity() {
    mNavigator.navigateToLogin(this);
  }

  /*TODO maybe extension function istead of repeated code?*/
  @Override public void showShortError(int id) {
    final String err = getString(id);
    Toast.makeText(this, err, Toast.LENGTH_SHORT).show();
  }

  @Override public void showLongError(int id) {
    final String err = getString(id);
    Toast.makeText(this, err, Toast.LENGTH_LONG).show();
  }
}