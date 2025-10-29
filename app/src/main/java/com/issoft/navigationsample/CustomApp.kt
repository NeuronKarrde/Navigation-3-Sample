package com.issoft.navigationsample

import android.app.Application
import com.issoft.navigationsample.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CustomApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CustomApp)
            modules(appModule)
        }
    }
}

