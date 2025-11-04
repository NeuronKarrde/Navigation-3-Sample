package com.issoft.navigationsample.features.login.splash.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.issoft.navigationsample.features.login.splash.viewmodels.SplashScreenViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
fun CustomSplashScreen(modifier : Modifier = Modifier, viewModel : SplashScreenViewModel = koinViewModel()) {
    if (viewModel.isLoading) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
}