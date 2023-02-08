package com.abhishek.login_screen_template

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

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

    private fun authenticate(){

    }
}