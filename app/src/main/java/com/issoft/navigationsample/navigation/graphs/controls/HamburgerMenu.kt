package com.issoft.navigationsample.navigation.graphs.controls

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun HamburgerMenu(drawerState : DrawerState, scope : CoroutineScope) {
    IconButton(onClick = {
        scope.launch {
            drawerState.apply {
                if (isClosed) open() else close()
            }
        }
    })
    {
        Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
    }
}

