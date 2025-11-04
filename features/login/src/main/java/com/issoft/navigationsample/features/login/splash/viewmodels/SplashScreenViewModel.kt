package com.issoft.navigationsample.features.login.splash.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.issoft.navigationsample.features.login.AuthRepository
import com.issoft.navigationsample.features.login.navigation.AppWalkThrough
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenViewModel(val backStack: NavBackStack<NavKey>, val repository: AuthRepository) : ViewModel() {
    var isLoading by mutableStateOf(true)
        private set
    init {
        viewModelScope.launch {
            try {
                val authed = repository.isAuthenticated()
//                val next = if (authed) Screen.NestedGraph else Screen.AppWalkthrough

                backStack.removeLastOrNull()
                backStack.add(AppWalkThrough)
            }
            finally {
                isLoading = false
            }
        }
    }
}