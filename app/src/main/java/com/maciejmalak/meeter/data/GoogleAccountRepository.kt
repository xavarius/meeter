package com.maciejmalak.meeter.data

import android.content.Intent
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInAccount


object GoogleAccountRepositorySingleton {
    var googleAccount: GoogleSignInAccount? = null

    fun setAccount(data: Intent) {
        var googleSignInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
        if (googleSignInResult.isSuccess) {
            googleAccount = googleSignInResult.signInAccount
        }
    }
}