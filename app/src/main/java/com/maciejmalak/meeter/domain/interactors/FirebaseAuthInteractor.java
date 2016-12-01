package com.maciejmalak.meeter.domain.interactors;

import com.maciejmalak.meeter.domain.interactors.base.Interactor;
import org.jetbrains.annotations.NotNull;

public interface FirebaseAuthInteractor extends Interactor {

  void setCallback(@NotNull Callback callback);

  interface Callback {
    void onFirebaseUserAuthenticated();
    void onFirebaseAuthError();
  }
}
