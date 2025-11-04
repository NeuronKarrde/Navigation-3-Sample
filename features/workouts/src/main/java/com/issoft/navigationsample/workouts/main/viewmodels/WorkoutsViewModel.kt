package com.issoft.navigationsample.workouts.main.viewmodels

import androidx.lifecycle.ViewModel
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.issoft.navigationsample.workouts.WorkoutsRepository
import com.issoft.navigationsample.workouts.navigation.WorkoutDetails
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class WorkoutsViewModel(private val backStack: NavBackStack<NavKey>, private val repository: WorkoutsRepository) : ViewModel() {
    private val _workoutsState = MutableStateFlow(
        repository.workoutList
    )
    val workoutsState = _workoutsState.asStateFlow()

    fun onAction(action: WorkoutsAction) {
        when(action) {
            is WorkoutsAction.OpenWorkout -> openWorkout(action.id)
            else -> Unit
        }
    }

    private fun openWorkout(id: String) {
        backStack.add(WorkoutDetails(id))
    }
}