package com.issoft.navigationsample.workouts.details.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.issoft.navigationsample.workouts.details.viewmodels.WorkoutDetailsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun WorkoutDetailsScreen(modifier: Modifier = Modifier,
                         viewModel: WorkoutDetailsViewModel = koinViewModel()) {
    val state by viewModel.workoutState.collectAsState()

    Scaffold(modifier = Modifier
        .fillMaxSize(),
        topBar = {
            TopAppBar(title = {Text("Workout ${state.title}")},
                navigationIcon = {
                    IconButton(onClick = {
                    })
                    {
                        Icon(imageVector = Icons.Filled.ChevronLeft, contentDescription = "Back")
                    }
                })
        },
        content = { padding ->
            Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                Text(
                    text = state.title,
                    fontSize = 26.sp
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = state.content,
                    fontSize = 18.sp
                )
            }
        })


}