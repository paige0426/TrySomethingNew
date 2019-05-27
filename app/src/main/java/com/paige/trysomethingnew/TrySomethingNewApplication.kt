package com.paige.trysomethingnew

import android.app.Application
import timber.log.Timber

class TrySomethingNewApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}