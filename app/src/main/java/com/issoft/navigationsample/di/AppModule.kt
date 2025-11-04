package com.issoft.navigationsample.di

import com.issoft.features.checkin.viewsmodels.CheckInViewModel
import com.issoft.navigationsample.controls.DialogService
import com.issoft.navigationsample.features.home.HomeViewModel
import com.issoft.navigationsample.features.login.AuthRepository
import com.issoft.navigationsample.features.login.AuthRepositoryImpl
import com.issoft.navigationsample.features.login.UserPreferences
import com.issoft.navigationsample.features.login.appwalkthrough.viewmodels.AppWalkthroughViewModel
import com.issoft.navigationsample.features.login.enteremail.viewmodels.EnterEmailViewModel
import com.issoft.navigationsample.features.login.splash.viewmodels.SplashScreenViewModel
import com.issoft.navigationsample.features.login.splash.views.CustomSplashScreen
import com.issoft.navigationsample.workouts.WorkoutsRepository
import com.issoft.navigationsample.workouts.details.viewmodels.WorkoutDetailsViewModel
import com.issoft.navigationsample.workouts.main.viewmodels.WorkoutsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    singleOf(::DialogService)
    single { UserPreferences(androidContext()) }
    singleOf(::WorkoutsRepository){bind<WorkoutsRepository>()}
    single<AuthRepository> { AuthRepositoryImpl(prefs = get()) }

    viewModelOf(::SplashScreenViewModel)
    viewModelOf(::AppWalkthroughViewModel)
    viewModelOf(::EnterEmailViewModel)
    viewModelOf(::HomeViewModel)
    viewModelOf(::WorkoutsViewModel)
    viewModelOf(::WorkoutDetailsViewModel)
    viewModelOf(::CheckInViewModel)
}