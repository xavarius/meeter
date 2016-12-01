package com.maciejmalak.meeter.domain.interactors.impl

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.maciejmalak.meeter.data.GoogleAccountRepositorySingleton
import com.maciejmalak.meeter.domain.interactors.FirebaseAuthInteractor
import com.maciejmalak.meeter.domain.interactors.base.AbstractInteractor
import javax.inject.Inject

/* W jaki sposób zrealizować logikę autoryzacji, tak, aby pozbyć się zależności android i firebase z inteactora.
 * Gdzie to powinno siedzieć jako operacja ciągnięcia danych z neta? */
class FireBaseAuthInteractorImpl
@Inject constructor(val mAuth : FirebaseAuth) : AbstractInteractor(), FirebaseAuthInteractor {
    private var mCallback: FirebaseAuthInteractor.Callback? = null

    private var mAccount: GoogleSignInAccount? = null

    private val mFirebaseAuthListener = { firebaseAuth : FirebaseAuth -> if (firebaseAuth.currentUser != null) post() else notifyError() }

    override fun run() {
        mAccount = GoogleAccountRepositorySingleton.googleAccount

        mAuth.addAuthStateListener(mFirebaseAuthListener)

        val credential = GoogleAuthProvider.getCredential(mAccount!!.idToken, null)
        mAuth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                post()
            } else {
                notifyError()
            }
        }
    }

    private fun post() {
        mMainThread.post { mCallback!!.onFirebaseUserAuthenticated() }
    }

    private fun notifyError() {
        mMainThread.post { mCallback!!.onFirebaseAuthError() }
    }

    override fun setCallback(callback: FirebaseAuthInteractor.Callback) {
        mCallback = callback
    }
}

