package com.issoft.navigationsample.features.home

sealed interface HomeAction {
    data object NavigateToCheckIn : HomeAction
}