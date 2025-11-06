package com.issoft.navigationsample.di

import com.issoft.navigationsample.controls.DialogService
import com.issoft.navigationsample.features.home.HomeViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    singleOf(::DialogService)
    viewModelOf(::HomeViewModel)
}