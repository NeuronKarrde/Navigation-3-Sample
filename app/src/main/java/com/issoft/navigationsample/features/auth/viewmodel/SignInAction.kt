package com.issoft.navigationsample.features.auth.viewmodel

sealed interface SignInAction {
    data object OnTogglePasswordVisibility: SignInAction
    data object OnLoginClick: SignInAction
    data class OnEmailChanged(val email : String): SignInAction
    data class OnPasswordChanged(val password : String): SignInAction
}