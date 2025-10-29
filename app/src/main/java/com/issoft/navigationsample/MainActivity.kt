package com.issoft.navigationsample

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import com.issoft.navigationsample.navigation.graphs.RootGraph
import com.issoft.navigationsample.ui.theme.NavigationSampleTheme
import kotlinx.coroutines.flow.MutableSharedFlow

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
class MainActivity : ComponentActivity() {
    private val deepLinkFlow = MutableSharedFlow<Uri>(replay = 1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationSampleTheme {
                val windowSizeClass : WindowSizeClass = calculateWindowSizeClass(this)
                RootGraph(deepLinkFlow, windowSizeClass)
            }
        }

        intent?.data?.let { deepLinkFlow.tryEmit(it) }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        intent?.data?.let { deepLinkFlow.tryEmit(it) }
    }
}