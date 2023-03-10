package com.abhishek.login_screen_template.model

import androidx.annotation.StringRes
import com.abhishek.login_screen_template.R

data class AuthenticationState(
    val authenticationMode: AuthenticationMode = AuthenticationMode.SIGN_IN,
    val email: String? = null,
    val password: String? = null,
    val passwordRequirements: List<PasswordRequirements> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
) {
    fun isFormValid(): Boolean {
        return password?.isNotEmpty() == true
                && email?.isNotEmpty() == true
                && (authenticationMode == AuthenticationMode.SIGN_IN
                || passwordRequirements.containsAll(PasswordRequirements.values().toList()))
    }
}


enum class PasswordRequirements(
    @StringRes val label: Int
) {
    EIGHT_CHARACTERS(R.string.password_requirements_character),
    DIGIT_CHARACTER(R.string.password_requirements_digit),
    CAPITAL_CHARACTER(R.string.password_requirements_upper_case)
}