package org.example.jetshop.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jetshop.composeapp.generated.resources.Res
import jetshop.composeapp.generated.resources.cross
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentFailedScreen(
    errorMessage: String,
    onRetryClick: () -> Unit,
    onGoHomeClick: () -> Unit
) {
    // Simple Scaffold with topBar for title and a bottom bar for the action button
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Payment Failed",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        fontFamily = Montserrat
                    )
                },
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Error Icon
                Image(
                    painter = painterResource(Res.drawable.cross),
                    contentDescription = "Error",
                    modifier = Modifier.size(100.dp)
                )

                Spacer_16dp()

                // Title text
                Text(
                    text = "Something went wrong!",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 25.sp,
                    modifier = Modifier.padding(horizontal = 24.dp),
                    textAlign = TextAlign.Center, fontFamily = Montserrat
                )

                Spacer_8dp()

                // Subtitle Text
                Text(
                    text = "We couldn't complete your payment. Please try again later.",
                    fontSize = 18.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(horizontal = 24.dp),
                    textAlign = TextAlign.Center, fontFamily = Montserrat
                )

                Spacer_16dp()

                // Detailed error message if any
                if (errorMessage.isNotEmpty()) {
                    Text(
                        text = errorMessage,
                        color = Color.Red,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(horizontal = 24.dp),
                        textAlign = TextAlign.Center, fontFamily = Montserrat
                    )

                    Spacer_24dp()
                }

                // Retry and Back buttons
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Retry Button
                    Button(
                        onClick = onRetryClick,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(text = "Retry", fontFamily = Montserrat)
                    }

                    // Go back Button
                    Button(
                        onClick = onGoHomeClick,
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
                    ) {
                        Text(text = "Home", fontFamily = Montserrat)
                    }
                }
            }
        }
    )
}


@Composable
fun ShowTextData() {
    PaymentFailedScreen(
        errorMessage = "errorMessage.value",
        onRetryClick = {

        },
        onGoHomeClick = {
            // Navigate back to the previous screen or home screen
        }
    )
}