package com.abhishek.login_screen_template

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class AuthenticationViewModel : ViewModel() {
    val uiState = MutableStateFlow(AuthenticationState())

    private fun toggleAuthenticationMode() {
        val authenticationMode = uiState.value.authenticationMode

        val newAuthenticationMode = if (authenticationMode == AuthenticationMode.SIGN_IN) {
            AuthenticationMode.SIGN_UP
        } else {
            AuthenticationMode.SIGN_IN
        }

        uiState.value = uiState.value.copy(
            authenticationMode = newAuthenticationMode
        )
    }

    private fun updateEmail(_email: String) {
        uiState.value = uiState.value.copy(
            email = _email
        )
    }

    private fun updatePassword(_password: String) {
        val requirements = mutableListOf<PasswordRequirements>()

        if (_password.length > 8) {
            requirements.add(PasswordRequirements.EIGHT_CHARACTERS)
        }

        if (_password.any { it.isDigit() }) {
            requirements.add(PasswordRequirements.DIGIT_CHARACTER)
        }

        if (_password.any { it.isUpperCase() }) {
            requirements.add(PasswordRequirements.CAPITAL_CHARACTER)
        }
        uiState.value = uiState.value.copy(
            password = _password,
            passwordRequirements = requirements
        )
    }

    private fun authenticate() {
        uiState.value = uiState.value.copy(
            isLoading = true
        )

        viewModelScope.launch(Dispatchers.IO) {
            delay(3000L)
        }
        viewModelScope.launch(Dispatchers.Main) {
            uiState.value = uiState.value.copy(
                isLoading = false,
                error = "Something went wrong"
            )
        }
    }

    private fun dismissError() {
        uiState.value = uiState.value.copy(
            error = null
        )
    }

    fun handleEvent(authenticationEvent: AuthenticationEvent) {
        when (authenticationEvent) {
            is AuthenticationEvent.ToggleAuthenticationMode -> {
                toggleAuthenticationMode()
            }
            is AuthenticationEvent.EmailChanged -> {
                updateEmail(authenticationEvent.emailAddress)
            }
            is AuthenticationEvent.PasswordChanged -> {
                updatePassword(authenticationEvent.password)
            }
            is AuthenticationEvent.Authenticate -> {
                authenticate()
            }
            is AuthenticationEvent.ErrorDismissed -> {
                dismissError()
            }
        }
    }
}