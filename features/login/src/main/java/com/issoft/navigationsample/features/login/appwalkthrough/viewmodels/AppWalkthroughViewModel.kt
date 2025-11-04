package com.issoft.navigationsample.features.login.appwalkthrough.viewmodels

import androidx.lifecycle.ViewModel
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.issoft.navigationsample.features.login.navigation.EnterEmail

class AppWalkthroughViewModel(val backStack: NavBackStack<NavKey>) : ViewModel() {
    fun onAction(action: AppWalkThroughAction) {
        when(action) {
            AppWalkThroughAction.ToEnterEmail -> openEnterEmail()
            AppWalkThroughAction.ToUnAuth -> openUnAuth()
            else -> Unit
        }
    }

    private fun openUnAuth() {
    }

    private fun openEnterEmail() {
        backStack.add(EnterEmail)
    }
}