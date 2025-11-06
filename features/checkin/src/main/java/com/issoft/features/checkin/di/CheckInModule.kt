package com.issoft.features.checkin.di

import com.issoft.features.checkin.viewsmodels.CheckInViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val checkInModule = module {
    viewModelOf(::CheckInViewModel)
}