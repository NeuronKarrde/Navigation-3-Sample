package com.issoft.navigationsample.workouts.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object WorkoutsMain : NavKey

@Serializable
data class WorkoutDetails(val id: String) : NavKey