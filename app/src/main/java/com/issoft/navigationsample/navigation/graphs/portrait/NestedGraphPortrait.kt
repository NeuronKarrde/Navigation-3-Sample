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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.issoft.core.navigation.BottomBarScreen
import com.issoft.navigationsample.features.home.navigation.Render
import com.issoft.navigationsample.features.profile.navigation.Render
import com.issoft.navigationsample.navigation.drawer.PFModalDrawerSheet
import com.issoft.navigationsample.navigation.graphs.controls.HamburgerMenu
import com.issoft.navigationsample.workouts.navigation.Render
import kotlinx.coroutines.flow.MutableSharedFlow


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NestedGraphPortrait(rootBackStack : NavBackStack<NavKey>, deepLinks: MutableSharedFlow<Uri>){

    val backStack = rememberNavBackStack(BottomBarScreen.Dashboard)

//    fun handleDeepLink(uri : Uri){
//        if (uri.path?.contains("mobile/workouts") == true){
//            backStack.add(NestedScreen.WorkoutDetails(workoutId = uri.path!!.substringAfterLast("/")))
//        }
//    }

//    LaunchedEffect(Unit) {
//        deepLinks.collect { handleDeepLink(it) }
//    }

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
                        HamburgerMenu(drawerState, scope)
                    })
             },
            bottomBar = {
                PFNavigationBar(backStack)
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
                    entry<BottomBarScreen.Dashboard>() { key -> key.Render(rootBackStack) }
                    entry<BottomBarScreen.WorkoutsMain>() { key -> key.Render(rootBackStack) }
                    entry<BottomBarScreen.MyJourneyMain>() { key -> key.Render(rootBackStack) }
                }
            )
        }
    }
}


