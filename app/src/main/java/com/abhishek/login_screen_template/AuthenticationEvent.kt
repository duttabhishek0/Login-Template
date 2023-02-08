package com.abhishek.login_screen_template

sealed class AuthenticationEvent {

    object ToggleAuthenticationMode: AuthenticationEvent()
}