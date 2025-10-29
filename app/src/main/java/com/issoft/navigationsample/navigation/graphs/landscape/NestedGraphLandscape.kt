package com.issoft.navigationsample.navigation.graphs.landscape

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.issoft.navigationsample.features.checkin.CheckInScreen
import com.issoft.navigationsample.features.home.HomeScreen
import com.issoft.navigationsample.features.profile.MyJourneyScreen
import com.issoft.navigationsample.features.workouts.workoutdetails.WorkoutDetailsScreen
import com.issoft.navigationsample.features.workouts.main.WorkoutsScreen
import com.issoft.navigationsample.navigation.navkeys.BottomBarScreen
import com.issoft.navigationsample.navigation.navkeys.NestedScreen
import kotlinx.coroutines.flow.MutableSharedFlow
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NestedGraphLandscape(deepLinks: MutableSharedFlow<Uri>){

    val backStack = rememberNavBackStack(BottomBarScreen.Home)

    fun handleDeepLink(uri : Uri){
        if (uri.path?.contains("mobile/workouts") == true){
            backStack.add(NestedScreen.WorkoutDetails(workoutId = uri.path!!.substringAfterLast("/")))
        }
    }

    LaunchedEffect(Unit) {
        deepLinks.collect { handleDeepLink(it) }
    }

    Row {
        CustomNavigationRail(backStack)
        NavDisplay(backStack = backStack,
            onBack = {backStack.removeLastOrNull()},
            entryDecorators = listOf(
                rememberViewModelStoreNavEntryDecorator(),
                rememberSaveableStateHolderNavEntryDecorator()
            ),
            entryProvider = entryProvider {
                entry<BottomBarScreen.Home>(){
                    HomeScreen(onCheckinClick = {
                        backStack.add(NestedScreen.CheckIn)
                    })
                }
                entry<BottomBarScreen.Workouts>(){
                    WorkoutsScreen(onWorkoutOpened = { id ->
                        backStack.add(NestedScreen.WorkoutDetails(workoutId = id))
                    })
                }
                entry<BottomBarScreen.MyJourney>(){
                    MyJourneyScreen()
                }
                entry<NestedScreen.CheckIn>(){
                    CheckInScreen()
                }
                entry<NestedScreen.WorkoutDetails>(){ key ->
                    WorkoutDetailsScreen(viewModel = koinViewModel{ parametersOf(key.workoutId) })
                }
            })
    }
}