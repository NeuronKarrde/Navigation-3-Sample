package com.issoft.navigationsample.features.home.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import com.issoft.navigationsample.features.home.HomeScreen
import com.issoft.navigationsample.features.workouts.main.WorkoutsScreen
import com.issoft.navigationsample.features.workouts.workoutdetails.WorkoutDetailsScreen
import com.issoft.navigationsample.navigation.navkeys.BottomBarScreen
import com.issoft.navigationsample.navigation.navkeys.NestedScreen
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun BottomBarScreen.Home.render(
    backStack: NavBackStack<NavKey>
) {
    return HomeScreen(onCheckinClick = {
        backStack.add(NestedScreen.CheckIn)
    })
}