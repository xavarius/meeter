package com.maciejmalak.meeter.di.modules;

import android.widget.Toast;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.maciejmalak.meeter.R;
import com.maciejmalak.meeter.di.scopes.PerActivity;
import com.maciejmalak.meeter.view.activities.BaseActivity;
import dagger.Module;
import dagger.Provides;

@Module public class ActivityModule {
  private final BaseActivity mActivity;

  public ActivityModule(final BaseActivity activity) {
    mActivity = activity;
  }

  @Provides @PerActivity BaseActivity providesActivity() {
    return this.mActivity;
  }

  /*TODO Is it a good place for that? Maybe some shorter scope or module??? Tip: how to scope for only one activity?*/
  @Provides @PerActivity GoogleApiClient providesGoogleSignAPI(final BaseActivity activity) {
    final GoogleSignInOptions gso =
        new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
    final String msg = activity.getResources().getString(R.string.sign_in_error);

    return new GoogleApiClient.Builder(activity).enableAutoManage(activity,
        view -> Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show())
        .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
        .build();
  }
}
