package com.issoft.navigationsample.features.login.enteremail.viewmodels

sealed interface EnterEmailEvent {
    data object LoginSuccess : EnterEmailEvent
    data class LoginFailed(val message : String) : EnterEmailEvent
}