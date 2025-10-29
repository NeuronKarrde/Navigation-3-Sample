package com.issoft.navigationsample.navigation.graphs

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.*
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.issoft.navigationsample.navigation.navkeys.NestedScreen
import java.util.Map.entry

class AppBarController {
    var title by mutableStateOf("")
    var actions: (@Composable RowScope.() -> Unit)? by mutableStateOf(null)
}

@Composable
fun rememberAppBarController() = remember { AppBarController() }

inline fun <reified T : NestedScreen> EntryProviderScope<NestedScreen>.screenWithAppBar(
    appBar: AppBarController,
    noinline title: (T) -> String,
    noinline actions: @Composable RowScope.(T) -> Unit = {},
    crossinline content: @Composable (T) -> Unit
) {
    entry<T> { key ->
        LaunchedEffect(key) { appBar.title = title(key) }
        SideEffect { appBar.actions = { actions(key) } }
        DisposableEffect(key) { onDispose { appBar.actions = null } }
        content(key)
    }
}