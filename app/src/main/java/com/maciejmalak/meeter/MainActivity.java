package com.maciejmalak.meeter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {
  @BindView(R.id.toolbar) protected Toolbar mToolbar;
  @BindView(R.id.fab) protected FloatingActionButton mFab;
  @BindView(R.id.google_sign_in_button) protected SignInButton mGoogleSignIn;

  private static final int GOOGLE_SIGN_RESULT = 1001;

  private GoogleApiClient mGoogleAPI;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    setSupportActionBar(mToolbar);

    buildSignInGoogle();

    mFab.setOnClickListener(
        view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            .setAction("Action", null)
            .show());
    mGoogleSignIn.setOnClickListener(view -> signInGoogle());
  }

  private void buildSignInGoogle() {
    final GoogleSignInOptions gso =
        new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();

    mGoogleAPI = new GoogleApiClient.Builder(this).enableAutoManage(this,
        view -> Toast.makeText(this, "sign in fuck up", Toast.LENGTH_SHORT).show())
        .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
        .build();
  }

  private void signInGoogle() {
    Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleAPI);
    startActivityForResult(signInIntent, GOOGLE_SIGN_RESULT);
  }

  @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (resultCode == RESULT_OK && requestCode == GOOGLE_SIGN_RESULT) {
      handleGoogleSignInResult(data);
    } else {
            /* TODO sign in fucked up*/
    }
  }

  private void handleGoogleSignInResult(@NonNull final Intent result) {
    final GoogleSignInResult googleSignInResult =
        Auth.GoogleSignInApi.getSignInResultFromIntent(result);
    if (googleSignInResult.isSuccess()) {
      final GoogleSignInAccount account = googleSignInResult.getSignInAccount();
      Toast.makeText(this, account.getDisplayName(), Toast.LENGTH_LONG).show();
    } else {
            /*TODO sign in fucked up*/
    }
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}