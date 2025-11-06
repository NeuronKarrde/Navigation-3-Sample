package com.issoft.navigationsample.features.login.di

import com.issoft.navigationsample.features.login.AuthRepository
import com.issoft.navigationsample.features.login.AuthRepositoryImpl
import com.issoft.navigationsample.features.login.UserPreferences
import com.issoft.navigationsample.features.login.appwalkthrough.viewmodels.AppWalkthroughViewModel
import com.issoft.navigationsample.features.login.enteremail.viewmodels.EnterEmailViewModel
import com.issoft.navigationsample.features.login.splash.viewmodels.SplashScreenViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val loginModule = module {
    single { UserPreferences(androidContext()) }
    single<AuthRepository> { AuthRepositoryImpl(prefs = get()) }

    viewModelOf(::SplashScreenViewModel)
    viewModelOf(::AppWalkthroughViewModel)
    viewModelOf(::EnterEmailViewModel)
}