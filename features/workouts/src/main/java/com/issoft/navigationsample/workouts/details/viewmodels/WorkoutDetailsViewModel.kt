package com.issoft.navigationsample.workouts.details.viewmodels

import androidx.lifecycle.ViewModel
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.issoft.navigationsample.workouts.WorkoutsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class WorkoutDetailsViewModel(private val workoutId: String, private val backStack: NavBackStack<NavKey>, private val repository: WorkoutsRepository) : ViewModel() {
    private val _workoutState = MutableStateFlow(
        repository.workoutList.first { it.id == workoutId }
    )
    val workoutState = _workoutState.asStateFlow()
}