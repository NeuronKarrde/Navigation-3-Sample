package com.issoft.navigationsample.features.login.appwalkthrough.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.issoft.navigationsample.features.login.appwalkthrough.viewmodels.AppWalkThroughAction
import com.issoft.navigationsample.features.login.appwalkthrough.viewmodels.AppWalkthroughViewModel
import com.issoft.navigationsample.features.login.enteremail.viewmodels.EnterEmailAction
import org.koin.androidx.compose.koinViewModel

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun AppWalkthroughScreen (viewModel : AppWalkthroughViewModel = koinViewModel()
){
    Scaffold(modifier = Modifier
        .fillMaxSize(),
        topBar = {
            TopAppBar(title = {Text("Walkthrough")})
                 },
        content = { padding ->
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(padding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center){
                Button(onClick = {viewModel.onAction(action = AppWalkThroughAction.ToEnterEmail)}) {
                    Text("Sign in")
                }
            }
        })
}