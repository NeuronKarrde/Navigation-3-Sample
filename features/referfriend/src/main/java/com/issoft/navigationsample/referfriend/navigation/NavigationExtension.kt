package com.issoft.navigationsample.referfriend.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.issoft.navigationsample.referfriend.myreferrals.views.MyReferralsScreen
import com.issoft.navigationsample.referfriend.referfriend.views.ReferFriendScreen

@Composable
private fun ReferFriend.Render(
    backStack: NavBackStack<NavKey>
) {
    return ReferFriendScreen()
}

@Composable
private fun MyReferrals.Render(
    backStack: NavBackStack<NavKey>
) {
    return MyReferralsScreen()
}

fun EntryProviderScope<NavKey>.installReferFriendGraph(backStack: NavBackStack<NavKey>){
    entry<ReferFriend> { key -> key.Render(backStack) }
    entry<MyReferrals> { key -> key.Render(backStack) }
}