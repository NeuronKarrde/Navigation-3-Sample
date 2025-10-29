package com.issoft.navigationsample.features.workouts.main.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import com.issoft.navigationsample.features.workouts.main.WorkoutsScreen
import com.issoft.navigationsample.features.workouts.workoutdetails.WorkoutDetailsScreen
import com.issoft.navigationsample.navigation.navkeys.BottomBarScreen
import com.issoft.navigationsample.navigation.navkeys.NestedScreen
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun NestedScreen.WorkoutDetails.render(
    backStack: NavBackStack<NavKey>,
    id: String
) {
    return WorkoutDetailsScreen(viewModel = koinViewModel { parametersOf(id) })
}

@Composable
fun BottomBarScreen.Workouts.render(
    backStack: NavBackStack<NavKey>
) {
    return WorkoutsScreen(onWorkoutOpened = { id -> backStack.add(NestedScreen.WorkoutDetails(workoutId = id)) })
}