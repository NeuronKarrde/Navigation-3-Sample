package com.issoft.features.checkin.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.issoft.core.navigation.Screen
import com.issoft.features.checkin.views.CheckInScreen
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
private fun Screen.CheckIn.Render(
    backStack: NavBackStack<NavKey>
) {
    return CheckInScreen(viewModel = koinViewModel { parametersOf(backStack) })
}

fun EntryProviderScope<NavKey>.installCheckInGraph(backStack: NavBackStack<NavKey>){
    entry<Screen.CheckIn> { key -> key.Render(backStack) }
}