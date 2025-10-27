package com.issoft.navigationsample.features.auth.viewmodel

sealed interface SignInEvent {
    data object LoginSuccess : SignInEvent
    data class LoginFailed(val message : String) : SignInEvent
}