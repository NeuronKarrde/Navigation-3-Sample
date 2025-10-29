package com.issoft.navigationsample.navigation.graphs.controls

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey

@Composable
fun BackButton(backStack : NavBackStack<NavKey>) {
    IconButton(onClick = {
        backStack.removeLastOrNull()
    })
    {
        Icon(imageVector = Icons.Filled.ChevronLeft, contentDescription = "Back")
    }
}