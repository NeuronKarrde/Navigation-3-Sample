package com.issoft.navigationsample.features.workouts.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.koinViewModel

@Composable
fun WorkoutsScreen(onWorkoutOpened: (String) -> Unit,
                   modifier : Modifier = Modifier,
                   viewModel: WorkoutsViewModel = koinViewModel(),
                   ) {
    val state by viewModel.workoutsState.collectAsState()
    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
    ) {
        items(state.size) { index ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onWorkoutOpened(state[index].id)
                    }
            ) {
                Text(
                    text = state[index].title,
                    fontSize = 18.sp
                )
                Text(
                    text = state[index].content,
                )
            }
        }
    }
}