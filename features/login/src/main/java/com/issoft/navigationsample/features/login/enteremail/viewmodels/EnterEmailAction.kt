package com.issoft.navigationsample.features.login.enteremail.viewmodels

sealed interface EnterEmailAction {
    data object OnTogglePasswordVisibility: EnterEmailAction
    data object OnLoginClick: EnterEmailAction
    data class OnEmailChanged(val email : String): EnterEmailAction
    data class OnPasswordChanged(val password : String): EnterEmailAction
}