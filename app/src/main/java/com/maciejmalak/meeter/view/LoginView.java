package com.maciejmalak.meeter.view;

public interface LoginView extends BaseView {
  void launchHomeActivity();
  void launchGoogleSignIn(int googleSignResult);
  void showErrLoginMessage();
}
