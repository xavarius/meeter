package com.maciejmalak.meeter.presenters

import android.content.Intent
import com.google.android.gms.auth.api.Auth
import com.maciejmalak.meeter.R
import com.maciejmalak.meeter.di.scopes.PerActivity
import com.maciejmalak.meeter.view.LoginView
import javax.inject.Inject

@PerActivity
class LoginPresenter @Inject constructor() : BasePresenter {
    val GOOGLE_SIGN_RESULT : Int = 1001
    lateinit var view : LoginView

    override fun onCreated() {}
    override fun onStarted() {}
    override fun onStopped() {}

    fun onGoogleSignBtnClicked() = view.launchGoogleSignIn(GOOGLE_SIGN_RESULT)

    override fun onActivityResulted(requestCode: Int, success: Boolean, data: Intent) {
        if (success && requestCode == GOOGLE_SIGN_RESULT) {
            handleGoogleSignInResult(data)
        } else {
            view.showErrLoginMessage()
        }
    }

    private fun handleGoogleSignInResult(result: Intent) {
        val googleSignInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(result)
        if (googleSignInResult.isSuccess) {
            val account = googleSignInResult.signInAccount
        } else {
            /*TODO sign in fucked up*/
        }
    }
}