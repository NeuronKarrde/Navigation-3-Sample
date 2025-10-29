package com.issoft.navigationsample.navigation.graphs.landscape

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.issoft.navigationsample.navigation.navkeys.BottomBarScreen
import com.issoft.navigationsample.navigation.navkeys.BottomBarScreenSaver
import com.issoft.navigationsample.navigation.navkeys.bottomBarItems


@Composable
fun CustomNavigationRail(backStack : NavBackStack<NavKey>, modifier: Modifier = Modifier) {
    var currentBottomBarScreen: BottomBarScreen by rememberSaveable(
        stateSaver = BottomBarScreenSaver
    ) { mutableStateOf(BottomBarScreen.Home) }

    NavigationRail(
        modifier = modifier.padding(start = 8.dp, end = 8.dp),
        containerColor = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            bottomBarItems.forEach { destination ->
                NavigationRailItem(
                    icon = {
                        Icon(
                            painter = painterResource(destination.icon),
                            contentDescription = null
                        )
                    },
                    label = {
                        Text(text = destination.title)
                    },
                    selected = currentBottomBarScreen == destination,
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
}