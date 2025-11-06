package com.issoft.navigationsample.navigation.drawer

import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.issoft.core.navigation.Screen
import com.issoft.navigationsample.features.login.AuthRepository
import kotlinx.coroutines.launch
import org.koin.compose.koinInject


@Composable
fun PFModalDrawerSheet(backStack: NavBackStack<NavKey>){
    val authRepository = koinInject<AuthRepository>()
    val scope = rememberCoroutineScope()
    ModalDrawerSheet(content = {
        NavigationDrawerItem(
            label = { Text(text = "Drawer Item") },
            selected = false,
            onClick = {
                backStack.clear()
                backStack.add(Screen.AppWalkThrough)
                scope.launch { authRepository.logout() }
            }
        )
    })
}