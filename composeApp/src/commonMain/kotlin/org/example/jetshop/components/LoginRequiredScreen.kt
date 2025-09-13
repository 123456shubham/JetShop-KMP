package org.example.jetshop.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import jetshop.composeapp.generated.resources.Res
import jetshop.composeapp.generated.resources.checklist
import org.example.jetshop.utils.IncludeApp
import org.jetbrains.compose.resources.painterResource

val Montserrat = androidx.compose.ui.text.font.FontFamily.Default

@Composable
fun LoginRequiredScreen() {
    val navigator= LocalNavigator.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp),
        contentAlignment = Alignment.Center // Centers the content in the screen
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp) // Increased height for Chrome padding
                .padding(horizontal = 16.dp),
        ) {
            Card(
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, Color.Gray),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(Color.White),
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(Res.drawable.checklist),
                        contentDescription = "",
                        modifier = Modifier.size(80.dp),
//                        tint = Color.Gray
                    )
                    Spacer_8dp()
                    Text(
                        text = "Sign in to explore and enjoy all the features of our app.",
                        fontWeight = FontWeight.Medium, textAlign = TextAlign.Center,
                        fontFamily = Montserrat,
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                    Spacer_12dp()
                    IncludeApp().CustomDivider()
                    Spacer_8dp()
                    Button(
                        onClick = {
//                            navController.navigate(Screen.Login.route)
                        },
                        modifier = Modifier
                            .fillMaxWidth(0.6f)
                            .height(50.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = "Login Now",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = Montserrat
                        )
                    }
                }
            }

            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .border(1.dp, Color.Gray, shape = RoundedCornerShape(8.dp))
                    .background(Color.White, shape = RoundedCornerShape(8.dp))
                    .padding(horizontal = 12.dp, vertical = 4.dp)
            ) {
                Text(
                    text = "Login Required",
                    color = Color(0xFF0086F9),
                    fontSize = 22.sp,
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
