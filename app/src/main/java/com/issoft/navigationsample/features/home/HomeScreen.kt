package com.issoft.navigationsample.features.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.LoadingIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.issoft.navigationsample.features.auth.viewmodel.SignInAction
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(onCheckinClick: () -> Unit, viewModel: HomeViewModel = koinViewModel()) {
    Column(modifier = Modifier
        .fillMaxSize().background(Color.Red),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        ){
        Button(onClick = onCheckinClick) {
            Text("Check in")
        }
    }
}