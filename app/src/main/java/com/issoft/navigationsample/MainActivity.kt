package com.issoft.navigationsample

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.issoft.navigationsample.presentation.RootGraph
import com.issoft.navigationsample.ui.theme.NavigationSampleTheme
import kotlinx.coroutines.flow.MutableSharedFlow

class MainActivity : ComponentActivity() {
    private val deepLinkFlow = MutableSharedFlow<Uri>(replay = 1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationSampleTheme {
                RootGraph(deepLinkFlow)
            }
        }

        intent?.data?.let { deepLinkFlow.tryEmit(it) }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        intent?.data?.let { deepLinkFlow.tryEmit(it) }
    }
}