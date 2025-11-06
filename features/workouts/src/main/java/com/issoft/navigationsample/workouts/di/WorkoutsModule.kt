package com.issoft.navigationsample.workouts.di

import com.issoft.navigationsample.workouts.WorkoutsRepository
import com.issoft.navigationsample.workouts.details.viewmodels.WorkoutDetailsViewModel
import com.issoft.navigationsample.workouts.main.viewmodels.WorkoutsViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val workoutsModule = module {
    singleOf(::WorkoutsRepository){bind<WorkoutsRepository>()}
    viewModelOf(::WorkoutsViewModel)
    viewModelOf(::WorkoutDetailsViewModel)
}