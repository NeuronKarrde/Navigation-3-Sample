package com.issoft.navigationsample.navigation.graphs.landscape

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.issoft.navigationsample.features.home.navigation.Render
import com.issoft.navigationsample.features.profile.navigation.Render
import com.issoft.navigationsample.navigation.navkeys.Home
import com.issoft.navigationsample.navigation.navkeys.MyJourney
import com.issoft.navigationsample.workouts.navigation.Render
import com.issoft.navigationsample.workouts.navigation.WorkoutsMain
import kotlinx.coroutines.flow.MutableSharedFlow

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NestedGraphLandscape(rootBackStack : NavBackStack<NavKey>, deepLinks: MutableSharedFlow<Uri>){

    val backStack = rememberNavBackStack(Home)

//    fun handleDeepLink(uri : Uri){
//        if (uri.path?.contains("mobile/workouts") == true){
//            backStack.add(NestedScreen.WorkoutDetails(workoutId = uri.path!!.substringAfterLast("/")))
//        }
//    }
//
//    LaunchedEffect(Unit) {
//        deepLinks.collect { handleDeepLink(it) }
//    }

    Row {
        CustomNavigationRail(backStack)
        NavDisplay(
            backStack = backStack,
            onBack = { backStack.removeLastOrNull() },
            entryDecorators = listOf(
                rememberViewModelStoreNavEntryDecorator(),
                rememberSaveableStateHolderNavEntryDecorator(),
            ),
            entryProvider = entryProvider {
                entry<Home>() { key -> key.Render(rootBackStack) }
                entry<WorkoutsMain>() { key -> key.Render(rootBackStack) }
                entry<MyJourney>() { key -> key.Render(rootBackStack) }
            }
        )
    }
}