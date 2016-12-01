package com.maciejmalak.meeter.presenters

import android.content.Intent
import com.maciejmalak.meeter.data.GoogleAccountRepositorySingleton
import com.maciejmalak.meeter.di.scopes.PerActivity
import com.maciejmalak.meeter.domain.executor.ThreadExecutor
import com.maciejmalak.meeter.domain.interactors.FirebaseAuthInteractor.Callback
import com.maciejmalak.meeter.domain.interactors.impl.FireBaseAuthInteractorImpl
import com.maciejmalak.meeter.view.LoginView
import javax.inject.Inject

@PerActivity
class LoginPresenter
    @Inject constructor(
            val firebaseAuthInteractor: FireBaseAuthInteractorImpl,
            val mThreadExecutor: ThreadExecutor)
    : BasePresenter, Callback {

    val GOOGLE_SIGN_RESULT: Int = 1001
    lateinit var view: LoginView

    init {
        /*TODO meh? I need to use Rx observables instead of MEH gulbr!!!!!! */
        firebaseAuthInteractor.setCallback(this)
    }

    override fun onCreated() {}
    override fun onStarted() {}
    override fun onStopped() {}

    override fun onActivityResulted(requestCode: Int, success: Boolean, data: Intent) {
        if (success && requestCode == GOOGLE_SIGN_RESULT) {
            GoogleAccountRepositorySingleton.setAccount(data) /*TODO meh*/
            mThreadExecutor.execute(firebaseAuthInteractor)
        } else {
            view.showErrLoginMessage()
        }
    }

    override fun onFirebaseAuthError() = view.showErrLoginMessage()
    override fun onFirebaseUserAuthenticated() = view.launchHomeActivity()

    fun onGoogleSignBtnClicked() = view.launchGoogleSignIn(GOOGLE_SIGN_RESULT)
}