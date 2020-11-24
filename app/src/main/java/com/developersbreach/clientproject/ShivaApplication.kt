@file:Suppress("unused")

package com.developersbreach.clientproject

import android.app.Application
import timber.log.Timber

class ShivaApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}