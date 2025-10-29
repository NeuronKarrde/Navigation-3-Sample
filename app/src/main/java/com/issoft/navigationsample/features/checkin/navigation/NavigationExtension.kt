package com.issoft.navigationsample.features.checkin.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import com.issoft.navigationsample.features.checkin.CheckInScreen
import com.issoft.navigationsample.features.home.HomeScreen
import com.issoft.navigationsample.features.profile.MyJourneyScreen
import com.issoft.navigationsample.features.workouts.main.WorkoutsScreen
import com.issoft.navigationsample.features.workouts.workoutdetails.WorkoutDetailsScreen
import com.issoft.navigationsample.navigation.navkeys.BottomBarScreen
import com.issoft.navigationsample.navigation.navkeys.NestedScreen
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun NestedScreen.CheckIn.render(
    backStack: NavBackStack<NavKey>
) {
    return CheckInScreen()
}