package com.issoft.navigationsample

import android.app.Application
import com.issoft.navigationsample.features.auth.AuthRepository
import com.issoft.navigationsample.features.auth.AuthRepositoryImpl
import com.issoft.navigationsample.features.auth.UserPreferences
import com.issoft.navigationsample.features.auth.viewmodel.SignInViewModel
import com.issoft.navigationsample.features.home.HomeViewModel
import com.issoft.navigationsample.features.workouts.WorkoutDetailsViewModel
import com.issoft.navigationsample.features.workouts.WorkoutsRepository
import com.issoft.navigationsample.features.workouts.WorkoutsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

class CustomApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CustomApp)
            modules(appModule)
        }
    }
}

val appModule = module {
    single { UserPreferences(androidContext()) }
    singleOf(::WorkoutsRepository){bind<WorkoutsRepository>()}
    single<AuthRepository> { AuthRepositoryImpl(prefs = get()) }
    viewModelOf(::SignInViewModel)
    viewModelOf(::HomeViewModel)
    viewModelOf(::WorkoutsViewModel)
    viewModelOf(::WorkoutDetailsViewModel)
}