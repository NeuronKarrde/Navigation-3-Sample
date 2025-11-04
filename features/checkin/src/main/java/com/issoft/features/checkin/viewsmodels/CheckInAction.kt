package com.issoft.features.checkin.viewsmodels

sealed interface CheckInAction {
    data object ToReferFriend : CheckInAction
}