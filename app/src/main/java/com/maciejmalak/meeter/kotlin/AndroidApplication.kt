package com.maciejmalak.meeter.kotlin

import android.app.Application
import com.squareup.leakcanary.LeakCanary

class AndroidApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        if (LeakCanary.isInAnalyzerProcess(this)) return

        LeakCanary.install(this)
    }
}