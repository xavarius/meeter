package com.maciejmalak.meeter.presenters

import android.content.Intent
import com.google.firebase.auth.FirebaseAuth
import com.maciejmalak.meeter.di.scopes.PerActivity
import com.maciejmalak.meeter.view.HomeMapView
import javax.inject.Inject

@PerActivity
class HomeMapPresenter @Inject constructor(val firebaseAuth: FirebaseAuth) : BasePresenter {
    lateinit var view : HomeMapView

    override fun onCreated() {
        if (firebaseAuth.currentUser == null) view.launchLoginActivity()
    }

    override fun onStarted() {}
    override fun onStopped() {}
    override fun onActivityResulted(requestCode: Int, success: Boolean, data: Intent) {}
    fun onFabButtonClicked() {}
}