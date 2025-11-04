package com.issoft.navigationsample.workouts.main.viewmodels

sealed interface WorkoutsAction {
    data class OpenWorkout(val id: String) : WorkoutsAction
}