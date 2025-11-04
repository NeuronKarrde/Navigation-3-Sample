package com.issoft.navigationsample.navigation.graphs

import android.net.Uri
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.issoft.core.navigation.Screen
import com.issoft.features.checkin.navigation.installCheckInGraph
import com.issoft.navigationsample.controls.DialogRenderer
import com.issoft.navigationsample.features.login.navigation.installLoginGraph
import com.issoft.navigationsample.referfriend.navigation.installReferFriendGraph
import com.issoft.navigationsample.workouts.navigation.installWorkoutsGraph
import kotlinx.coroutines.flow.MutableSharedFlow

@Composable
fun RootGraph(deepLinks: MutableSharedFlow<Uri>, windowSize : WindowSizeClass){
    val nestedDeepLinkFlow = MutableSharedFlow<Uri>(replay = 1)

    val backStack = rememberNavBackStack(Screen.Splash)

//    fun handleDeepLink(uri : Uri){
//        if(runBlocking { repo.isAuthenticated() }){
//            return
//        }
//        if (uri.path?.contains("mobile/workouts") == true){
//            backStack.clear()
//            backStack.add(Screen.NestedGraph)
//            uri.let { nestedDeepLinkFlow.tryEmit(it) }
//        }
//    }

//    LaunchedEffect(Unit) {
//        deepLinks.collect { handleDeepLink(it) }
//    }

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull()},
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator(),
        ),
        entryProvider = entryProvider {
            installLoginGraph(backStack)
            installWorkoutsGraph(backStack)
            installCheckInGraph(backStack)
            installReferFriendGraph(backStack)
            entry<Screen.NestedGraph> {
                NestedGraph(backStack, nestedDeepLinkFlow, windowSize)
            }
        }
    )

    DialogRenderer()
}