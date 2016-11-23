package com.maciejmalak.meeter.domain.interactors;

/*TODO Jak wstrzyknąć zarządzanie MainThread żeby móc łatwo zrobić POSTA? */
public interface FirebaseAuthInteractor {
  interface Callback {
    void onFirebaseUserAuthenticated();
    void onFirebaseAuthError();
  }
}
