package org.example.jetshop.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import jetshop.composeapp.generated.resources.Res
import jetshop.composeapp.generated.resources.montserrat_bold
import jetshop.composeapp.generated.resources.montserrat_light
import jetshop.composeapp.generated.resources.montserrat_medium
import jetshop.composeapp.generated.resources.montserrat_regular
import jetshop.composeapp.generated.resources.montserrat_semibold
import org.jetbrains.compose.resources.Font

// ✅ Proper Montserrat loader for Compose Multiplatform
@Composable
fun rememberMontserrat(): FontFamily {
    return FontFamily(
        Font(Res.font.montserrat_light, weight = FontWeight.Light),
        Font(Res.font.montserrat_regular, weight = FontWeight.Normal),
        Font(Res.font.montserrat_medium, weight = FontWeight.Medium),
        Font(Res.font.montserrat_semibold, weight = FontWeight.SemiBold),
        Font(Res.font.montserrat_bold, weight = FontWeight.Bold),
    )
}

// ✅ Centralized Typography
@Composable
fun AppTypography(): Typography {
    val montserrat = rememberMontserrat()

    return Typography(
        // Titles
        titleLarge = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = montserrat,
            lineHeight = 32.sp
        ),
        headlineSmall = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = montserrat,
            lineHeight = 28.sp
        ),
        titleMedium = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = montserrat,
            lineHeight = 26.sp
        ),
        titleSmall = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = montserrat,
            lineHeight = 24.sp
        ),

        // Body
        bodyMedium = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = montserrat,
            lineHeight = 24.sp
        ),
        bodySmall = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = montserrat,
            lineHeight = 20.sp
        ),

        // Labels
        labelSmall = TextStyle(
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = montserrat,
            lineHeight = 16.sp
        )
    )
}
