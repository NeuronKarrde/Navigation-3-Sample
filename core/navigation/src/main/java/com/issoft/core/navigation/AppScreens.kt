package com.issoft.core.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed class Screen : NavKey {
    @Serializable
    data object NestedGraph : Screen()

    @Serializable
    data object CheckIn : Screen()

    @Serializable
    data object Splash : Screen()
    @Serializable
    data object AppWalkThrough : Screen()
    @Serializable
    data object EnterEmail : Screen()

    @Serializable
    data object ReferFriend : Screen()

    @Serializable
    data object MyReferrals : Screen()
    @Serializable
    data class WorkoutDetails(val id: String) : Screen()
}

@Serializable
sealed class BottomBarScreen : NavKey {
    @Serializable
    data object Dashboard : BottomBarScreen()
    @Serializable
    data object WorkoutsMain : BottomBarScreen()
    @Serializable
    data object MyJourneyMain : BottomBarScreen()
}