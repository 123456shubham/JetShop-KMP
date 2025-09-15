package org.example.jetshop.screen.register

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import io.ktor.client.HttpClient
import jetshop.composeapp.generated.resources.Res
import jetshop.composeapp.generated.resources.email
import jetshop.composeapp.generated.resources.logo
import jetshop.composeapp.generated.resources.telephone
import jetshop.composeapp.generated.resources.upload
import jetshop.composeapp.generated.resources.user
import kotlinx.coroutines.launch
import org.example.jetshop.HideBottomBar
import org.example.jetshop.components.AppButton
import org.example.jetshop.components.CircularImage
import org.example.jetshop.components.CustomOutlinedTextField
import org.example.jetshop.components.CustomPasswordField
import org.example.jetshop.components.LoadingButton
import org.example.jetshop.components.Spacer_10dp
import org.example.jetshop.components.Spacer_20dp
import org.example.jetshop.components.Spacer_8dp
import org.example.jetshop.components.SubtitleLarge
import org.example.jetshop.components.TitleMedium
import org.example.jetshop.model.auth.register.RegisterRequest
import org.example.jetshop.remote.ApiResponse
import org.example.jetshop.repo.auth.RegisterRepo
import org.example.jetshop.screen.OTP
import org.example.jetshop.utils.CommonFunction.RegisterCheckbox
import org.example.jetshop.viewModel.AuthViewModel


object Register : Screen, HideBottomBar{
    @Composable
    override fun Content() {

        val viewModel: AuthViewModel = rememberScreenModel { AuthViewModel(RegisterRepo()) }

        Box(modifier = Modifier.fillMaxWidth().padding(start = 10.dp, end = 10.dp).navigationBarsPadding()){
            RegisterScreen("123",viewModel)
        }

    }
}

@Composable
fun RegisterScreen(referCode: String, viewModel: AuthViewModel) {
    val navigator = LocalNavigator.current
    val keyboardController = LocalSoftwareKeyboardController.current

    // Local states
    var firstName by rememberSaveable { mutableStateOf("") }
    var lastName by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var phone by rememberSaveable { mutableStateOf("") }
    val cleanReferCode = if (referCode == "{code}") "" else referCode
    var refer_Code by rememberSaveable { mutableStateOf(cleanReferCode) }
    var password by rememberSaveable { mutableStateOf("") }
    var isChecked by rememberSaveable { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    // Collect register state
    val registerState by viewModel.registerState.collectAsState()
    LaunchedEffect(registerState) {
        when (registerState) {
            is ApiResponse.Loading -> {
                println("⏳ Loading...")
            }

            is ApiResponse.Success -> {
                val data = (registerState as ApiResponse.Success).data
                println("✅ Register Success: $data")
                // TODO: navigate to OTP screen here
                navigator?.push(OTP)
                viewModel.resetRegisterState()
            }

            is ApiResponse.Error -> {
                val error = (registerState as ApiResponse.Error).message
                println("❌ Register Error: $error")
                scope.launch {
                    snackbarHostState.showSnackbar(
                        message = error,
                        withDismissAction = true
                    )
                }
                viewModel.resetRegisterState()
            }

            else -> {}
        }
    }

    // Optional: Show status in UI
    val message = when (registerState) {
        is ApiResponse.Success -> "✅ Success!"
        is ApiResponse.Error -> (registerState as ApiResponse.Error).message
        is ApiResponse.Loading -> "⏳ Loading..."
        else -> ""
    }


    Box(modifier = Modifier.fillMaxSize()) {



    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState()) // vertical scrolling
            .fillMaxSize()
            .padding(10.dp)
            .background(
                Color(0xFFF7F7F7)
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularImage(
            imageRes = Res.drawable.logo
        )
        Spacer_20dp()

        TitleMedium(text = "Register")
        Spacer_8dp()
        SubtitleLarge("Create a new account to get started")
        Spacer_20dp()
        CustomOutlinedTextField(
            value = firstName,
            onValueChange = { firstName = it },
            label = "First Name",
            leadingIcon = Res.drawable.user,
        )

        Spacer_10dp()

        CustomOutlinedTextField(
            value = lastName,
            onValueChange = { lastName = it },
            label = "Last Name",
            leadingIcon = Res.drawable.user,
        )

        Spacer_10dp()

        CustomOutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = "Email",
            leadingIcon = Res.drawable.email,
            keyboardType = KeyboardType.Email,
        )

        Spacer_10dp()

        CustomOutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            label = "Phone",
            leadingIcon = Res.drawable.telephone,
            keyboardType = KeyboardType.Phone
        )

        Spacer_10dp()

        CustomOutlinedTextField(
            value = refer_Code,
            onValueChange = { refer_Code = it },
            label = "Refer Code",
            leadingIcon = Res.drawable.upload,
            keyboardType = KeyboardType.Text
        )

        Spacer_10dp()

        CustomPasswordField(
            value = password, onValueChange = { password = it }, iserror = false
        )
        Spacer_10dp()
        RegisterCheckbox(
            checked = isChecked,
            onCheckedChange = { isChecked = it },
            onTermsClick = {
                // Open Terms URL
//                val url = URLEncoder.encode(termsUrl.value, "UTF-8")
//                navController.navigate(Screen.WebViewScreen.route+"/Terms & Conditions/$url")
            },
            onPrivacyClick = {
                // Open Privacy URL
//                val url = URLEncoder.encode(privacyUrl.value, "UTF-8")
//                navController.navigate(Screen.WebViewScreen.route+"/Privacy Policy/$url")
            }
        )

        Spacer_20dp()
        AppButton(
            text = "Register",
            onClick = {
                keyboardController?.hide()
                if (firstName.isBlank() || lastName.isBlank() || email.isBlank() || phone.isBlank() || password.isBlank()) {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = "️⚠️ All fields are required!",
                            withDismissAction = true
                        )
                    }
                } else if (!isChecked) {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = "️⚠️ Please accept terms and conditions!",
                            withDismissAction = true
                        )
                    }
                } else {
                    // ✅ Call API here
                    val request = RegisterRequest(
                        email = email,
                        first_name = firstName,
                        last_name = lastName,
                        password = password,
                        phone = phone,
                        referred_by = refer_Code
                    )

                    viewModel.register(request)

                }
            },
            modifier = Modifier.fillMaxWidth()
        )

    }

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
        )
    }
}
