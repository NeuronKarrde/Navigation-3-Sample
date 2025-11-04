package com.issoft.features.checkin.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.issoft.features.checkin.views.controls.QrImage
import com.issoft.features.checkin.viewsmodels.CheckInAction
import com.issoft.features.checkin.viewsmodels.CheckInViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun CheckInScreen(viewModel : CheckInViewModel = koinViewModel() ) {
    Column(modifier = Modifier
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){
        QrImage("Test Qr code", Modifier.padding(40.dp))
        Button(onClick = { viewModel.onAction(action = CheckInAction.ToReferFriend)}) {
            Text(text = "Refer a Friend")
        }
    }
}
