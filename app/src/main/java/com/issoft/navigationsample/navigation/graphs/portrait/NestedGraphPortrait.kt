package com.issoft.navigationsample.navigation.graphs.portrait

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.issoft.navigationsample.features.checkin.navigation.render
import com.issoft.navigationsample.features.home.navigation.render
import com.issoft.navigationsample.features.profile.navigation.render
import com.issoft.navigationsample.features.workouts.main.navigation.render
import com.issoft.navigationsample.navigation.drawer.PFModalDrawerSheet
import com.issoft.navigationsample.navigation.graphs.controls.BackButton
import com.issoft.navigationsample.navigation.graphs.controls.HamburgerMenu
import com.issoft.navigationsample.navigation.navkeys.BottomBarScreen
import com.issoft.navigationsample.navigation.navkeys.NestedScreen
import kotlinx.coroutines.flow.MutableSharedFlow


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NestedGraphPortrait(deepLinks: MutableSharedFlow<Uri>){

    val backStack = rememberNavBackStack(BottomBarScreen.Home)

    fun handleDeepLink(uri : Uri){
        if (uri.path?.contains("mobile/workouts") == true){
            backStack.add(NestedScreen.WorkoutDetails(workoutId = uri.path!!.substringAfterLast("/")))
        }
    }

    LaunchedEffect(Unit) {
        deepLinks.collect { handleDeepLink(it) }
    }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {PFModalDrawerSheet()},
    )
    {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {Text(backStack.last().toString())},
                    navigationIcon = {
                        if (backStack.size == 1) HamburgerMenu(drawerState, scope) else BackButton(backStack)
                    })
             },
            bottomBar = {
                if (backStack.size == 1)  PFNavigationBar(backStack) else null
            }
        ){
            NavDisplay(
                backStack = backStack,
                onBack = { backStack.removeLastOrNull() },
                entryDecorators = listOf(
                    rememberViewModelStoreNavEntryDecorator(),
                    rememberSaveableStateHolderNavEntryDecorator(),
                ),
                entryProvider = entryProvider {
                    entry<BottomBarScreen.Home>() { key -> key.render(backStack) }
                    entry<BottomBarScreen.Workouts>() { key -> key.render(backStack) }
                    entry<BottomBarScreen.MyJourney>() { key -> key.render(backStack) }
                    entry<NestedScreen.CheckIn>() { key -> key.render(backStack) }
                    entry<NestedScreen.WorkoutDetails>() { key -> key.render(backStack, key.workoutId) }
                }
            )
        }
    }
}


