package org.example.jetshop.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jetshop.composeapp.generated.resources.Res
import jetshop.composeapp.generated.resources.montserrat_medium
import org.jetbrains.compose.resources.Font

// ✅ Helper to load Montserrat properly
@Composable
fun rememberMontserrat(): FontFamily {
    return FontFamily(Font(Res.font.montserrat_medium))
}

@Composable
fun TitleLarge(text: String, modifier: Modifier = Modifier) {
    val montserrat = rememberMontserrat()
    Text(
        text = text,
        style = TextStyle(
            fontSize = 30.sp,
            fontFamily = montserrat,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1E88E5) // Primary Color
        ),
        modifier = modifier
    )
}

@Composable
fun TitleMedium(text: String, modifier: Modifier = Modifier) {
    val montserrat = rememberMontserrat()
    Text(
        text = text,
        style = TextStyle(
            fontSize = 24.sp,
            fontFamily = montserrat,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        ),
        modifier = modifier
    )
}

@Composable
fun TitleSmall(text: String, modifier: Modifier = Modifier) {
    val montserrat = rememberMontserrat()
    Text(
        text = text,
        style = TextStyle(
            fontSize = 20.sp,
            fontFamily = montserrat,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black
        ),
        modifier = modifier
    )
}

@Composable
fun SubtitleLarge(text: String, modifier: Modifier = Modifier) {
    val montserrat = rememberMontserrat()
    Text(
        text = text,
        style = TextStyle(
            fontSize = 18.sp,
            fontFamily = montserrat,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        ),
        modifier = modifier
    )
}

@Composable
fun SubtitleMedium(text: String, modifier: Modifier = Modifier) {
    val montserrat = rememberMontserrat()
    Text(
        text = text,
        style = TextStyle(
            fontSize = 16.sp,
            fontFamily = montserrat,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        ),
        modifier = modifier
    )
}

@Composable
fun SubtitleSmall(text: String, modifier: Modifier = Modifier) {
    val montserrat = rememberMontserrat()
    Text(
        text = text,
        style = TextStyle(
            fontSize = 14.sp,
            fontFamily = montserrat,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        ),
        modifier = modifier
    )
}

@Composable
fun LabelLarge(text: String, modifier: Modifier = Modifier) {
    val montserrat = rememberMontserrat()
    Text(
        text = text,
        style = TextStyle(
            fontSize = 16.sp,
            fontFamily = montserrat,
            fontWeight = FontWeight.Normal,
            color = Color.Gray
        ),
        modifier = modifier
    )
}

@Composable
fun LabelMedium(text: String, modifier: Modifier = Modifier) {
    val montserrat = rememberMontserrat()
    Text(
        text = text,
        style = TextStyle(
            fontSize = 14.sp,
            fontFamily = montserrat,
            fontWeight = FontWeight.Normal,
            color = Color.Gray
        ),
        modifier = modifier
    )
}

@Composable
fun LabelSmall(text: String, modifier: Modifier = Modifier) {
    val montserrat = rememberMontserrat()
    Text(
        text = text,
        style = TextStyle(
            fontSize = 12.sp,
            fontFamily = montserrat,
            fontWeight = FontWeight.Normal,
            color = Color.Gray
        ),
        modifier = modifier
    )
}

@Composable
fun ShowText() {
    Column(modifier = Modifier.padding(16.dp)) {
//        TitleLarge(stringResource( "Hello World"))

        TitleMedium("Register")
        TitleSmall("Small Title")

        Spacer_8dp()

        SubtitleLarge("Large Subtitle")
        SubtitleMedium("Medium Subtitle")
        SubtitleSmall("Small Subtitle")

        Spacer_8dp()

        LabelLarge("Large Label")
        LabelMedium("Medium Label")
        LabelSmall("Small Label")
    }
}
