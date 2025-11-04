package com.issoft.navigationsample.workouts.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.issoft.navigationsample.workouts.details.views.WorkoutDetailsScreen
import com.issoft.navigationsample.workouts.main.views.WorkoutsScreen
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
public fun WorkoutsMain.Render(
    backStack: NavBackStack<NavKey>
) {
    return WorkoutsScreen(viewModel = koinViewModel { parametersOf(backStack) })
}

@Composable
private fun WorkoutDetails.Render(
    backStack: NavBackStack<NavKey>,
    id : String
) {
    return WorkoutDetailsScreen(viewModel = koinViewModel { parametersOf(backStack, id) })
}

fun EntryProviderScope<NavKey>.installWorkoutsGraph(backStack: NavBackStack<NavKey>){
    entry<WorkoutsMain> { key -> key.Render(backStack) }
    entry<WorkoutDetails> { key -> key.Render(backStack, key.id) }
}