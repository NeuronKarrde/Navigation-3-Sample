package com.issoft.navigationsample

import android.app.Application
import com.issoft.features.checkin.di.checkInModule
import com.issoft.navigationsample.di.appModule
import com.issoft.navigationsample.features.login.di.loginModule
import com.issoft.navigationsample.referfriend.di.referFriendModule
import com.issoft.navigationsample.workouts.di.workoutsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CustomApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CustomApp)
            modules(appModule, checkInModule, loginModule, referFriendModule, workoutsModule)
        }
    }
}

