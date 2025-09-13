package org.example.jetshop.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import jetshop.composeapp.generated.resources.Res
import jetshop.composeapp.generated.resources.montserrat_bold
import jetshop.composeapp.generated.resources.montserrat_light
import jetshop.composeapp.generated.resources.montserrat_medium
import jetshop.composeapp.generated.resources.montserrat_regular
import jetshop.composeapp.generated.resources.montserrat_semibold
import org.jetbrains.compose.resources.Font

@Composable
fun ProductTypography(): ProductTypographySet {
    val montserrat = rememberMontserrat()
    return ProductTypographySet(montserrat)
}

// ✅ Hold all product text styles
class ProductTypographySet(private val montserrat: FontFamily) {
    val prodTitleMedium = TextStyle(
        fontSize = 14.sp,
        fontFamily = montserrat,
        fontWeight = FontWeight.SemiBold
    )

    val prodSubtitle = TextStyle(
        fontSize = 14.sp,
        fontFamily = montserrat,
        fontWeight = FontWeight.Normal
    )

    val prodPriceBold = TextStyle(
        fontSize = 14.sp,
        fontFamily = montserrat,
        fontWeight = FontWeight.Bold
    )

    val prodDiscountPrice = TextStyle(
        fontSize = 12.sp,
        fontFamily = montserrat,
        fontWeight = FontWeight.Normal,
        textDecoration = TextDecoration.LineThrough
    )

    val prodDescription = TextStyle(
        fontSize = 13.sp,
        fontFamily = montserrat,
        fontWeight = FontWeight.Normal
    )

    val prodRating = TextStyle(
        fontSize = 12.sp,
        fontFamily = montserrat,
        fontWeight = FontWeight.Normal
    )

    val productQuantity = TextStyle(
        fontSize = 14.sp,
        fontFamily = montserrat,
        fontWeight = FontWeight.Medium
    )

    val categoryName = TextStyle(
        fontSize = 14.sp,
        fontFamily = montserrat,
        fontWeight = FontWeight.Medium
    )

    val sectionTitleBold = TextStyle(
        fontSize = 16.sp,
        fontFamily = montserrat,
        fontWeight = FontWeight.SemiBold
    )

    val labelSmall = TextStyle(
        fontSize = 12.sp,
        fontFamily = montserrat,
        fontWeight = FontWeight.Normal
    )
}
