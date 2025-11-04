package com.issoft.navigationsample.features.login.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.issoft.navigationsample.features.login.appwalkthrough.views.AppWalkthroughScreen
import com.issoft.navigationsample.features.login.enteremail.views.EnterEmailScreen
import com.issoft.navigationsample.features.login.splash.views.CustomSplashScreen
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
private fun Splash.Render(
    backStack: NavBackStack<NavKey>
) {
    return CustomSplashScreen(viewModel = koinViewModel { parametersOf(backStack) })
}

@Composable
private fun AppWalkThrough.Render(
    backStack: NavBackStack<NavKey>
) {
    return AppWalkthroughScreen(viewModel = koinViewModel { parametersOf(backStack) })
}

@Composable
private fun EnterEmail.Render(
    backStack: NavBackStack<NavKey>
) {
    return EnterEmailScreen(viewModel = koinViewModel { parametersOf(backStack) })
}

fun EntryProviderScope<NavKey>.installLoginGraph(backStack: NavBackStack<NavKey>){
    entry<Splash> { key -> key.Render(backStack) }
    entry<AppWalkThrough> { key -> key.Render(backStack) }
    entry<EnterEmail> { key -> key.Render(backStack) }
}