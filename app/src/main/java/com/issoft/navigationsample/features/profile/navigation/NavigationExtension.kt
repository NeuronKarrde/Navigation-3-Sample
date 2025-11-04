package com.issoft.navigationsample.features.profile.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.issoft.navigationsample.features.profile.MyJourneyScreen
import com.issoft.navigationsample.navigation.navkeys.MyJourney

@Composable
fun MyJourney.Render(
    backStack: NavBackStack<NavKey>
) {
    return MyJourneyScreen()
}