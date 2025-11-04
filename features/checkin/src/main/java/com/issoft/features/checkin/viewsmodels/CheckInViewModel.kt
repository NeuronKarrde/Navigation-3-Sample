package com.issoft.features.checkin.viewsmodels

import androidx.lifecycle.ViewModel
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.issoft.core.navigation.Screen

class CheckInViewModel(private val backStack: NavBackStack<NavKey>) : ViewModel() {
    fun onAction(action: CheckInAction) {
        when(action) {
            CheckInAction.ToReferFriend -> openReferFriend()
            else -> Unit
        }
    }

    private fun openReferFriend() {
        backStack.add(Screen.ReferFriend)
    }
}