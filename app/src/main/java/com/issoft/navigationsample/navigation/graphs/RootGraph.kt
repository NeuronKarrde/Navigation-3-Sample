package com.issoft.navigationsample.navigation.graphs

import android.net.Uri
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.issoft.navigationsample.controls.DialogRenderer
import com.issoft.navigationsample.features.auth.AuthRepository
import com.issoft.navigationsample.features.auth.views.SignInScreen
import com.issoft.navigationsample.features.auth.views.WalkthroughScreen
import com.issoft.navigationsample.navigation.navkeys.Screen
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.runBlocking
import org.koin.compose.koinInject

@Composable
fun RootGraph(deepLinks: MutableSharedFlow<Uri>, windowSize : WindowSizeClass){
    val nestedDeepLinkFlow = MutableSharedFlow<Uri>(replay = 1)
    val repo: AuthRepository = koinInject()

    val firstScreen = if(runBlocking { repo.isAuthenticated() }) Screen.NestedGraph else Screen.AppWalkthrough
    val backStack = rememberNavBackStack(firstScreen)

    fun handleDeepLink(uri : Uri){
        if(runBlocking { repo.isAuthenticated() }){
            return
        }
        if (uri.path?.contains("mobile/workouts") == true){
            backStack.clear()
            backStack.add(Screen.NestedGraph)
            uri.let { nestedDeepLinkFlow.tryEmit(it) }
        }
    }

    LaunchedEffect(Unit) {
        deepLinks.collect { handleDeepLink(it) }
    }

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull()},
//        entryDecorators = listOf(
//            rememberViewModelStoreNavEntryDecorator(),
//            rememberSaveableStateHolderNavEntryDecorator(),
//        ),
        entryProvider = entryProvider {
            entry<Screen.AppWalkthrough> {
                WalkthroughScreen(onLoginStarted = {
                    backStack.add(Screen.SignIn)
                })
            }
            entry<Screen.SignIn> {
                SignInScreen(onLoginSuccess = {
                    backStack.add(Screen.NestedGraph)
                    backStack.removeAll(listOf(Screen.AppWalkthrough, Screen.SignIn))
                })
            }
            entry<Screen.NestedGraph> {
                NestedGraph(nestedDeepLinkFlow, windowSize)
            }
        }
    )

    DialogRenderer()
}