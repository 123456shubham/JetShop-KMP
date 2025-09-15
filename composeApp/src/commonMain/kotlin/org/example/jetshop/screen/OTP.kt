package org.example.jetshop.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import org.example.jetshop.HideBottomBar
import org.example.jetshop.components.OTPInputField
import org.example.jetshop.ui.theme.white

object OTP : Screen, HideBottomBar {
    @Composable
    override fun Content() {
        var otp by rememberSaveable { mutableStateOf("") }

        Box(
            modifier = Modifier.fillMaxSize().background(white),
            contentAlignment = Alignment.Center
        ) {
            OTPInputRow(
                length = 4,
                onOtpEntered = { code ->
                    otp = code
                }
            )
        }
    }
}

@Composable
fun OTPInputRow(
    length: Int = 4,
    onOtpEntered: (String) -> Unit,

) {
    val focusRequesters = List(length) { FocusRequester() }
    var otpValues by rememberSaveable { mutableStateOf(List(length) { "" }) }

    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxSize(),
    ) {
        otpValues.forEachIndexed { index, value ->
            OTPInputField(
                value = value,
                focusRequester = focusRequesters[index],
                onValueChange = { newValue ->
                    otpValues = otpValues.toMutableList().also { it[index] = newValue }
                    if (otpValues.all { it.isNotEmpty() }) {
                        onOtpEntered(otpValues.joinToString(""))
                    }
                },

            )
        }
    }
}
