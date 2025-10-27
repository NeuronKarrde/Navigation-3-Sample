package com.issoft.navigationsample.features.workouts

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class WorkoutsViewModel(private val repository: WorkoutsRepository) : ViewModel() {
    private val _workoutsState = MutableStateFlow(
        repository.workoutList
    )
    val workoutsState = _workoutsState.asStateFlow()
}