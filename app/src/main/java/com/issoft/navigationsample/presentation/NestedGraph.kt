package com.issoft.navigationsample.presentation

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.ui.res.painterResource
import androidx.navigation3.runtime.*
import androidx.navigation3.ui.NavDisplay
import com.issoft.navigationsample.features.home.HomeScreen
import com.issoft.navigationsample.features.profile.MyJourneyScreen
import com.issoft.navigationsample.features.workouts.WorkoutsScreen
import com.issoft.navigationsample.navigation.BottomBarScreen

import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import com.issoft.navigationsample.features.auth.AuthRepository
import com.issoft.navigationsample.features.checkin.CheckInScreen
import com.issoft.navigationsample.features.workouts.WorkoutDetailsScreen
import com.issoft.navigationsample.navigation.NestedScreen
import com.issoft.navigationsample.navigation.Screen
import kotlinx.coroutines.flow.MutableSharedFlow
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NestedGraph(deepLinks: MutableSharedFlow<Uri>){
    val backStack = rememberNavBackStack(BottomBarScreen.Home)

    fun handleDeepLink(uri : Uri){
        if (uri.path?.contains("mobile/workouts") == true){
            backStack.add(NestedScreen.WorkoutDetails(workoutId = uri.path!!.substringAfterLast("/")))
        }
    }

    LaunchedEffect(Unit) {
        deepLinks.collect { handleDeepLink(it) }
    }

    Scaffold(
        topBar = {TopAppBar(title = {Text("Home")})},
        bottomBar = {
            if (backStack.count() == 1)  PFNavigationBar(backStack) else null
        }
    ){
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