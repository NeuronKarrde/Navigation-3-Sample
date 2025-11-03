package com.issoft.navigationsample.controls

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import org.koin.compose.koinInject

data class DialogOptions(
    val title: String? = null,
    val message: String,
    val onPrimaryButtonClick: (() -> Unit)? = null,
    val onSecondaryButtonClick: (() -> Unit)? = null
)

class DialogService {
    private val _dialog = mutableStateOf<DialogOptions?>(null)
    val dialog by _dialog

    fun showDialog(options: DialogOptions) {
        _dialog.value = options
    }

    fun hideDialog() {
        _dialog.value = null
    }
}

@Composable
fun DialogRenderer(
    dialogService: DialogService = koinInject<DialogService>()
) {
    dialogService.dialog?.let { options ->
        AlertDialog(
            onDismissRequest = dialogService::hideDialog,
            title = {
                options.title?.let {
                    Text(it)
                }
            },
            text = { Text(options.message) },
            confirmButton = {
                TextButton(onClick = dialogService::hideDialog) {
                    Text("OK")
                }
            }
        )
    }
}
