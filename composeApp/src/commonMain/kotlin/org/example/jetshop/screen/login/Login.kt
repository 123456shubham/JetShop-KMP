package org.example.jetshop.screen.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import jetshop.composeapp.generated.resources.Res
import jetshop.composeapp.generated.resources.email
import jetshop.composeapp.generated.resources.logo
import org.example.jetshop.HideBottomBar
import org.example.jetshop.components.AppButton
import org.example.jetshop.components.BlurView
import org.example.jetshop.components.CircularImage
import org.example.jetshop.components.CustomOutlinedTextField
import org.example.jetshop.components.CustomPasswordField
import org.example.jetshop.components.Spacer_12dp
import org.example.jetshop.components.Spacer_16dp
import org.example.jetshop.components.TitleMedium
import org.example.jetshop.screen.ForgetPassword
import org.example.jetshop.screen.register.Register
import org.example.jetshop.ui.theme.BluePrimary


object  Login : Screen , HideBottomBar{
    @Composable
    override fun Content() {
        LoginScreen()
    }
}



@Composable
fun LoginScreen() {

    val navigator= LocalNavigator.current
    BlurView()
    val keyboardController = LocalSoftwareKeyboardController.current
    var email by rememberSaveable{ mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .background(Color.White, shape = RoundedCornerShape(8.dp))
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularImage(
                imageRes = Res.drawable.logo
            )
            Spacer_16dp()

            TitleMedium(text = "Login")
            Spacer_16dp()

            CustomOutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = "Email",
                leadingIcon = Res.drawable.email, // Pass resource directly
                modifier = Modifier.fillMaxWidth(),
            )


            Spacer_12dp()

            CustomPasswordField(
                value = password,
                onValueChange = { password = it },
                iserror = false
            )

            Spacer_16dp()

            AppButton("Login", modifier = Modifier.fillMaxWidth(), background = BluePrimary)
            Spacer_12dp()

            TextButton(onClick = {
                navigator?.push(ForgetPassword)

            }) {
                Text("Forgot Password?", color = BluePrimary)
            }
//            Spacer_8dp()

            TextButton(onClick = {
                navigator?.push(Register)

            }) {
                Text("New user? Register Now", color = BluePrimary,)
            }

            /*// ✅ Show success data (example)
            loginData?.let {
                Text("Welcome, ${it.message}", style = AppTypography.bodyLarge)
            }*/
        }
    }

}