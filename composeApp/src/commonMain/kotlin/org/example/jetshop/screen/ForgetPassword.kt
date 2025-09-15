package org.example.jetshop.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import jetshop.composeapp.generated.resources.Res
import jetshop.composeapp.generated.resources.forgot_bg
import org.example.jetshop.HideBottomBar
import org.example.jetshop.components.AppButton
import org.example.jetshop.components.Montserrat
import org.example.jetshop.ui.theme.white
import org.jetbrains.compose.resources.painterResource

object ForgetPassword : Screen, HideBottomBar {
    @Composable
    override fun Content() {
        ForgetPasswordScreen()
    }
}

@Composable
fun ForgetPasswordScreen(){
    var email by rememberSaveable { mutableStateOf("") }
    var emailError by rememberSaveable { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxSize()) {
        // Background Image
        Image(
            painter = painterResource(Res.drawable.forgot_bg), // Replace with your image resource
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = androidx.compose.ui.layout.ContentScale.Crop
        )

        // Gradient Overlay
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black.copy(alpha = 1.5f)),
                        startY = 300f
                    )
                )
        )

        // Content at Bottom
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(20.dp)
        ) {
            Text(
                text = "Forgot Password",
                fontSize = 26.sp,
//                style = appTypography.displayLarge,
                color = white,
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Enter your registered email address to reset your password.",
//                style = AppTypography.titleLarge,
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 14.sp,
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                    emailError = it.isEmpty()
                },
                label = {
                    Text(
                        "Email Address",
                        color = Color.White,
                        fontFamily = Montserrat,  // Apply Montserrat font
                        fontWeight = FontWeight.Medium,
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                isError = emailError,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Black.copy(alpha = 0.3f),
                    unfocusedContainerColor = Color.Black.copy(alpha = 0.3f),
                    focusedIndicatorColor = Color.White,
                    unfocusedIndicatorColor = Color.White.copy(alpha = 0.7f),
                    cursorColor = Color.White
                ),
                textStyle = TextStyle(
                    fontFamily = Montserrat,  // Apply Montserrat font
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                ),
                visualTransformation = VisualTransformation.None
            )

            if (emailError) {
                Text(text = "Email can't be empty!", color = Color.Red, fontSize = 12.sp)
            }

            Spacer(modifier = Modifier.height(20.dp))

            AppButton("Reset Password", modifier = Modifier.fillMaxWidth(), onClick = {})

            Spacer(modifier = Modifier.height(40.dp))
        }

    }
}