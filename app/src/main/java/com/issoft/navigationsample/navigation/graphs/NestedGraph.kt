package com.issoft.navigationsample.navigation.graphs

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import com.issoft.navigationsample.navigation.graphs.landscape.NestedGraphLandscape
import com.issoft.navigationsample.navigation.graphs.portrait.NestedGraphPortrait

import kotlinx.coroutines.flow.MutableSharedFlow

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NestedGraph(deepLinks: MutableSharedFlow<Uri>, windowSize : WindowSizeClass) {
    when (windowSize.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            NestedGraphPortrait(deepLinks)
        }
        WindowWidthSizeClass.Expanded -> {
            NestedGraphLandscape(deepLinks)
        }
    }
}

