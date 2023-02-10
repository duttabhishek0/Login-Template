package com.abhishek.login_screen_template.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.abhishek.login_screen_template.R
import com.abhishek.login_screen_template.model.AuthenticationMode

@Composable
fun AuthenticationTitle(
    modifier: Modifier = Modifier,
    authenticationMode: AuthenticationMode
) {
    Text(
        text = stringResource(
            id = if (authenticationMode == AuthenticationMode.SIGN_IN) {
                R.string.label_sign_in
            } else {
                R.string.label_sign_up
            }
        ),
        fontSize = 24.sp,
        fontWeight = FontWeight.Black
    )
}