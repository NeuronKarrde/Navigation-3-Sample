package com.issoft.navigationsample.features.workouts.main

import androidx.lifecycle.ViewModel
import com.issoft.navigationsample.features.workouts.WorkoutsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class WorkoutsViewModel(private val repository: WorkoutsRepository) : ViewModel() {
    private val _workoutsState = MutableStateFlow(
        repository.workoutList
    )
    val workoutsState = _workoutsState.asStateFlow()
}