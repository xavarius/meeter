package com.maciejmalak.meeter.view.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

public class MainActivity extends BaseActivity implements HomeMapView {
  @Inject HomeMapPresenter mPresenter;
  @BindView(R.id.toolbar) Toolbar mToolbar;

  public static Intent getCallingIntent(@NotNull Context context) {
    final Intent launch = new Intent(context, MainActivity.class);
    launch.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
    return launch;
  }

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mPresenter.onCreated();
  }

  @Override protected void setupView() {
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    setSupportActionBar(mToolbar);
  }

  @Override protected void initializeInjector() {
    final ActivityComponent component = DaggerActivityComponent.builder()
        .applicationComponent(getApplicationComponent())
        .activityModule(getActivityModule())
        .build();
    component.inject(this);
  }

  @Override protected void injectViewToPresenter() {
    mPresenter.setView(this);
  }

  @OnClick(R.id.fab) void onFabClicked() {
    mPresenter.onFabButtonClicked();
  }

  @Override public void launchLoginActivity() {
    mNavigator.navigateToLogin(this);
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