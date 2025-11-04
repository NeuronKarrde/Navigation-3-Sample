package com.issoft.navigationsample.features.login.enteremail.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.issoft.core.navigation.Screen
import com.issoft.navigationsample.features.login.AuthRepository
import com.issoft.navigationsample.features.login.LoginResult
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EnterEmailViewModel(private val backStack: NavBackStack<NavKey>, private val repo: AuthRepository
//, private val dialogService: DialogService
) : ViewModel() {
    private val _state = MutableStateFlow(EnterEmailState())
    val state : StateFlow<EnterEmailState> = _state.asStateFlow()

//    val state : StateFlow<SignInState>
//        field = MutableStateFlow(SignInState())

    private val _events = MutableSharedFlow<EnterEmailEvent>(replay = 0, extraBufferCapacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val events : SharedFlow<EnterEmailEvent> = _events

    fun onAction(action: EnterEmailAction) {
        when(action) {
            EnterEmailAction.OnLoginClick -> login()
            EnterEmailAction.OnTogglePasswordVisibility -> togglePasswordVisibility()
            is EnterEmailAction.OnEmailChanged -> emailChanged(action.email)
            is EnterEmailAction.OnPasswordChanged -> passwordChanged(action.password)
            else -> Unit
        }
    }

    private fun login() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            when(val result = repo.login(state.value.email, state.value.password)){
                is LoginResult.Error -> {
                    _state.update { it.copy(isLoading = false, error = result.message) }
//                    dialogService.showDialog(DialogOptions("Error", "Login failed"))
                }
                is LoginResult.Success -> {
                    backStack.clear()
                    backStack.add(Screen.NestedGraph)
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