package com.issoft.navigationsample.features.workouts.workoutdetails

import androidx.lifecycle.ViewModel
import com.issoft.navigationsample.features.workouts.WorkoutsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class WorkoutDetailsViewModel(private val workoutId: String, private val repository: WorkoutsRepository) : ViewModel() {
    private val _workoutState = MutableStateFlow(
        repository.workoutList.first { it.id == workoutId }
    )
    val workoutState = _workoutState.asStateFlow()
}