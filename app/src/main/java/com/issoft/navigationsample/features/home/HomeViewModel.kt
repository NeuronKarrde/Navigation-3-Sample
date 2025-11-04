package com.issoft.navigationsample.features.home

import androidx.lifecycle.ViewModel
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.issoft.core.navigation.Screen

class HomeViewModel(private val backStack: NavBackStack<NavKey>) : ViewModel() {
    fun onAction(action: HomeAction) {
        when(action) {
            HomeAction.NavigateToCheckIn -> openCheckIn()
            else -> Unit
        }
    }

    private fun openCheckIn() {
        backStack.add(Screen.CheckIn)
    }
}