package com.giussepr.rxjava.app

import android.app.Application
import com.giussepr.rxjava.BuildConfig
import timber.log.Timber

class RxJavaApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}