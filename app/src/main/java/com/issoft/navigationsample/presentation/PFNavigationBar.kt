package com.issoft.navigationsample.presentation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.issoft.navigationsample.navigation.BottomBarScreen
import com.issoft.navigationsample.navigation.BottomBarScreenSaver
import com.issoft.navigationsample.navigation.bottomBarItems

@Composable
fun PFNavigationBar(backStack : NavBackStack<NavKey>) {
    var currentBottomBarScreen: BottomBarScreen by rememberSaveable(
        stateSaver = BottomBarScreenSaver
    ) { mutableStateOf(BottomBarScreen.Home) }
    NavigationBar {
        bottomBarItems.forEach { destination ->
            NavigationBarItem(
                selected = currentBottomBarScreen == destination,
                icon = {
                    Icon(
                        painter = painterResource(destination.icon),
                        contentDescription = "$destination icon"
                    )
                },
                onClick = {
                    if (backStack.lastOrNull() != destination) {
                        if (backStack.lastOrNull() in bottomBarItems) {
                            backStack.removeAt(backStack.lastIndex)
                        }
                        backStack.add(destination)
                        currentBottomBarScreen = destination
                    }
                }
            )
        }
    }
}