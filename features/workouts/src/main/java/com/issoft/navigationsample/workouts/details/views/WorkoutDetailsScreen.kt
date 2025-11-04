package com.issoft.navigationsample.workouts.details.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
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
fun WorkoutDetailsScreen(modifier: Modifier = Modifier,
                         viewModel: WorkoutDetailsViewModel = koinViewModel()) {
    val state by viewModel.workoutState.collectAsState()
    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
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
}