package com.issoft.navigationsample.features.profile.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.issoft.core.navigation.BottomBarScreen
import com.issoft.navigationsample.features.profile.MyJourneyScreen

@Composable
fun BottomBarScreen.MyJourneyMain.Render(
    backStack: NavBackStack<NavKey>
) {
    return MyJourneyScreen()
}