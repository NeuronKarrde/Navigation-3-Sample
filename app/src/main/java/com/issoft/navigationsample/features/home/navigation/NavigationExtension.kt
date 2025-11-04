package com.issoft.navigationsample.features.home.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.issoft.core.navigation.BottomBarScreen
import com.issoft.core.navigation.Screen
import com.issoft.navigationsample.features.home.HomeScreen
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun BottomBarScreen.Dashboard.Render(
    backStack: NavBackStack<NavKey>
) {
    return HomeScreen(viewModel = koinViewModel { parametersOf(backStack) })
}