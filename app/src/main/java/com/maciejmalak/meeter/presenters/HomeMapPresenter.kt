package com.maciejmalak.meeter.presenters

import android.content.Intent
import com.maciejmalak.meeter.view.HomeMapView
import com.maciejmalak.meeter.di.scopes.PerActivity
import javax.inject.Inject

@PerActivity
class HomeMapPresenter @Inject constructor() : BasePresenter {
    lateinit var view : HomeMapView

    fun onFabButtonClicked() = view.launchLoginActivity()

    override fun onCreated() {}
    override fun onStarted() {}
    override fun onStopped() {}

    override fun onActivityResulted(requestCode: Int, success: Boolean, data: Intent) {}
}