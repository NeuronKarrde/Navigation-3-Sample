package com.issoft.navigationsample.presentation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.issoft.navigationsample.features.auth.AuthRepository
import com.issoft.navigationsample.features.auth.SignInScreen
import com.issoft.navigationsample.features.auth.WalkthroughScreen
import com.issoft.navigationsample.navigation.Screen
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import org.koin.compose.koinInject

@Composable
fun RootGraph(deepLinks: MutableSharedFlow<Uri>){
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
                NestedGraph(nestedDeepLinkFlow)
            }
        }
    )
}