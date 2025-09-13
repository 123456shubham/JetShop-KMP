package org.example.jetshop.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
@Composable
fun appMainTypography(): AppTypographySet {
    val montserrat = rememberMontserrat()
    return AppTypographySet(montserrat)
}

class AppTypographySet(private val montserrat: FontFamily) {
    val appTitle = TextStyle(
        fontSize = 22.sp,
        fontFamily = montserrat,
        fontWeight = FontWeight.Bold
    )
    val sectionHeader = TextStyle(
        fontSize = 17.sp,
        fontFamily = montserrat,
        fontWeight = FontWeight.Bold
    )
    val subHeader = TextStyle(
        fontSize = 18.sp,
        fontFamily = montserrat,
        fontWeight = FontWeight.SemiBold
    )
    val bodyText = TextStyle(
        fontSize = 15.sp,
        fontFamily = montserrat,
        fontWeight = FontWeight.Normal
    )
    val caption = TextStyle(
        fontSize = 12.sp,
        fontFamily = montserrat,
        fontWeight = FontWeight.Normal
    )
    val seeAllText = TextStyle(
        fontSize = 14.sp,
        fontFamily = montserrat,
        fontWeight = FontWeight.Medium
    )
    val labelLarge = TextStyle(
        fontSize = 16.sp,
        fontFamily = montserrat,
        fontWeight = FontWeight.SemiBold
    )
}
