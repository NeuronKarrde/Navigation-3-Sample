package com.issoft.navigationsample.features.login.appwalkthrough.viewmodels

import com.issoft.navigationsample.features.login.enteremail.viewmodels.EnterEmailAction

sealed interface AppWalkThroughAction {
    data object ToEnterEmail: AppWalkThroughAction
    data object ToUnAuth: AppWalkThroughAction
}