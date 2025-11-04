package com.issoft.navigationsample.referfriend.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.issoft.core.navigation.Screen
import com.issoft.navigationsample.referfriend.myreferrals.views.MyReferralsScreen
import com.issoft.navigationsample.referfriend.referfriend.views.ReferFriendScreen

@Composable
private fun Screen.ReferFriend.Render(
    backStack: NavBackStack<NavKey>
) {
    return ReferFriendScreen()
}

@Composable
private fun Screen.MyReferrals.Render(
    backStack: NavBackStack<NavKey>
) {
    return MyReferralsScreen()
}

fun EntryProviderScope<NavKey>.installReferFriendGraph(backStack: NavBackStack<NavKey>){
    entry<Screen.ReferFriend> { key -> key.Render(backStack) }
    entry<Screen.MyReferrals> { key -> key.Render(backStack) }
}