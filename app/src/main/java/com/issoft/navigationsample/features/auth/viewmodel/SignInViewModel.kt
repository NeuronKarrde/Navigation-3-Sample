package com.issoft.navigationsample.features.auth.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.issoft.navigationsample.features.auth.AuthRepository
import com.issoft.navigationsample.features.auth.LoginResult
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignInViewModel(private val repo: AuthRepository) : ViewModel() {
    private val _state = MutableStateFlow(SignInState())
    val state : StateFlow<SignInState> = _state.asStateFlow()

//    val state : StateFlow<SignInState>
//        field = MutableStateFlow(SignInState())

    private val _events = MutableSharedFlow<SignInEvent>(replay = 0, extraBufferCapacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val events : SharedFlow<SignInEvent> = _events

    fun onAction(action: SignInAction) {
        when(action) {
            SignInAction.OnLoginClick -> login()
            SignInAction.OnTogglePasswordVisibility -> togglePasswordVisibility()
            is SignInAction.OnEmailChanged -> emailChanged(action.email)
            is SignInAction.OnPasswordChanged -> passwordChanged(action.password)
            else -> Unit
        }
    }

    private fun login() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            when(val result = repo.login(state.value.email, state.value.password)){
                is LoginResult.Error -> {
                    _state.update { it.copy(isLoading = false, error = result.message) }
                    _events.emit(SignInEvent.LoginFailed(result.message))
                }
                is LoginResult.Success -> {
                    _state.update { it.copy(isLoading = false, session = result.session) }
                    _events.emit(SignInEvent.LoginSuccess)
                }
            }
        }
    }

    private fun emailChanged(email : String) {
        _state.update { it.copy(email = email) }
    }

    private fun passwordChanged(password : String) {
        _state.update { it.copy(password = password) }
    }

    private fun togglePasswordVisibility() {
        _state.update { it.copy(isPasswordVisible = !it.isPasswordVisible) }
    }
}