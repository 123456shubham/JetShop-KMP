package org.example.jetshop.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jetshop.composeapp.generated.resources.Res
import jetshop.composeapp.generated.resources.left_arrow
import org.example.jetshop.ui.theme.ProductTypography
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolbarWithBackButtonAndTitle(
    title: String,
    onBackClick: () -> Unit = {},
    showBackButton: Boolean = true
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp) // standard toolbar height
            .background(White),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (showBackButton) {
            IconButton(onClick = onBackClick) {
                Image(
                    painter = painterResource(Res.drawable.left_arrow),
                    contentDescription = "Back",
                    modifier = Modifier.size(18.dp) // no extra padding
                )
            }
        }

        Text(
            text = title,
            fontSize = 16.sp,
//            style = TitleSmall, // your typography
            style = ProductTypography().prodPriceBold,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Composable
fun BackArrow(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(30.dp)
            .clip(CircleShape)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(Res.drawable.left_arrow),
            contentDescription = "Arrow Back",
            modifier = Modifier.size(20.dp)
        )
    }
}

@Composable
fun ToolbarPreview() {
    ToolbarWithBackButtonAndTitle(title = "OTP Verification",
        onBackClick = { /* Handle back click here */ })
}
