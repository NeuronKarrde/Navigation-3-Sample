package com.issoft.navigationsample.features.workouts

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class WorkoutDetailsViewModel(private val workoutId: String, private val repository: WorkoutsRepository) : ViewModel() {
    private val _workoutState = MutableStateFlow(
        repository.workoutList.first { it.id == workoutId }
    )
    val workoutState = _workoutState.asStateFlow()
}