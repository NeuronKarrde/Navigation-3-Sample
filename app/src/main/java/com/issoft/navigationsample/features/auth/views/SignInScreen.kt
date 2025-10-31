package com.issoft.navigationsample.features.auth.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LoadingIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.issoft.navigationsample.controls.AlertDialogExample
import com.issoft.navigationsample.features.auth.viewmodel.SignInAction
import com.issoft.navigationsample.features.auth.viewmodel.SignInEvent
import com.issoft.navigationsample.features.auth.viewmodel.SignInViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.androidx.compose.koinViewModel

@Composable
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
fun SignInScreen (onLoginSuccess: () -> Unit,
                  viewModel : SignInViewModel = koinViewModel()
){
    val state by viewModel.state.collectAsStateWithLifecycle()
    val openAlertDialog = remember { mutableStateOf(false) }

    // We can handle login success using event from viewmodel
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(viewModel.events, lifecycleOwner.lifecycle) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            withContext(Dispatchers.Main.immediate) {
                viewModel.events.collect{ e ->
                    if (e is SignInEvent.LoginSuccess) {
                        onLoginSuccess()
                    }
                    if (e is SignInEvent.LoginFailed) {
                        openAlertDialog.value = true
                    }
                }
            }
        }
    }

    Scaffold(modifier = Modifier
        .fillMaxSize(),
        topBar = {
            TopAppBar(title = {Text("Log in")})
        },
        content = { padding ->
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(padding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center){

                OutlinedTextField(
                    value = state.email,
                    onValueChange = { viewModel.onAction(SignInAction.OnEmailChanged(it)) },
                    label = { Text("Email") },
                    singleLine = true,
                    enabled = !state.isLoading,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    modifier = Modifier.fillMaxWidth(),
                    isError = state.error != null,
                    supportingText = {
                        if (state.error != null) {
                            Text(text = state.error!!)
                        }
                    },
                )

                Spacer(Modifier.height(12.dp))

                OutlinedTextField(
                    value = state.password,
                    onValueChange = { viewModel.onAction(SignInAction.OnPasswordChanged(it)) },
                    label = { Text("Password") },
                    singleLine = true,
                    enabled = !state.isLoading,
                    visualTransformation = if (state.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { viewModel.onAction(SignInAction.OnTogglePasswordVisibility) }) {
                            Icon(
                                if (state.isPasswordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                                contentDescription = null
                            )
                        }
                    },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(20.dp))

                Button(onClick = { viewModel.onAction(SignInAction.OnLoginClick) }) {
                   Text("Sign in")
                }
                if (state.isLoading)
                    LoadingIndicator(color = Color.Blue)

                when{
                    openAlertDialog.value -> {
                        AlertDialogExample(
                            onDismissRequest = { openAlertDialog.value = false },
                            onConfirmation = {
                                openAlertDialog.value = false
                                println("Confirmation registered") // Add logic here to handle confirmation.
                            },
                            dialogTitle = "Alert dialog example",
                            dialogText = "This is an example of an alert dialog with buttons.",
                            icon = Icons.Default.Info
                        )
                    }
                }
            }
        })
}