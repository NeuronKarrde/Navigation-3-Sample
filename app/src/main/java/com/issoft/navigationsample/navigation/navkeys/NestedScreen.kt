package com.issoft.navigationsample.navigation.navkeys

import androidx.compose.runtime.saveable.Saver
import androidx.navigation3.runtime.NavKey
import com.issoft.navigationsample.R
import kotlinx.serialization.Serializable

val bottomBarItems = listOf<BottomBarScreen>(
    BottomBarScreen.Home,
    BottomBarScreen.Workouts,
    BottomBarScreen.MyJourney
)

open class NestedScreen: NavKey {
    @Serializable
    data object CheckIn : NestedScreen()
    @Serializable
    data class WorkoutDetails(val workoutId: String) : NestedScreen()
}

@Serializable
sealed class BottomBarScreen(
    val icon: Int,
    val title: String,
): NestedScreen() {
    @Serializable
    data object Home : BottomBarScreen(
        icon = R.drawable.home,
        title = "Home"
    )

    @Serializable
    data object Workouts : BottomBarScreen(
        icon = R.drawable.search,
        title = "Workouts"
    )

    @Serializable
    data object MyJourney : BottomBarScreen(
        icon = R.drawable.person,
        title = "My Journey"
    )
}

val BottomBarScreenSaver = Saver<BottomBarScreen, String>(
    save = { it::class.simpleName ?: "Unknown" },
    restore = {
        when (it) {
            BottomBarScreen.Home::class.simpleName -> BottomBarScreen.Home
            BottomBarScreen.Workouts::class.simpleName -> BottomBarScreen.Workouts
            BottomBarScreen.MyJourney::class.simpleName -> BottomBarScreen.MyJourney
            else -> BottomBarScreen.Home
        }
    }
)