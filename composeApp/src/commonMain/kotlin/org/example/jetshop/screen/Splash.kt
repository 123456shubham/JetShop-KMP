package org.example.jetshop.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import jetshop.composeapp.generated.resources.Res
import jetshop.composeapp.generated.resources.logo
import jetshop.composeapp.generated.resources.logo_trans
import kotlinx.coroutines.delay
import org.example.jetshop.screen.login.Login
import org.example.jetshop.ui.theme.white
import org.jetbrains.compose.resources.painterResource

object Splash : Screen {
    @Composable
    override fun Content() {
     SplashScreen()
    }
}

@Composable
fun SplashScreen(){

    val navigator = LocalNavigator.currentOrThrow
    LaunchedEffect(Unit){
        delay(2000)
        navigator.replace(Login)
    }

    Box(modifier = Modifier.fillMaxSize().background(white)){

        Image(painterResource(Res.drawable.logo_trans), null, modifier = Modifier.align(Alignment.Center))
    }
}