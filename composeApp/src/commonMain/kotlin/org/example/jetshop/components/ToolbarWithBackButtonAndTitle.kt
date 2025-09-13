package org.example.jetshop.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import jetshop.composeapp.generated.resources.Res
import jetshop.composeapp.generated.resources.left_arrow
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolbarWithBackButtonAndTitle(
    title: String,
    onBackClick: () -> Unit = {}, // Default empty lambda
    showBackButton: Boolean = true // Control visibility
) {
    CenterAlignedTopAppBar(
        title = {
            TitleSmall(title)
        },
        navigationIcon = {
            if (showBackButton) {
                IconButton(onClick = onBackClick) {
                    Image(
                        painter = painterResource(Res.drawable.left_arrow),
                        contentDescription = "Back",
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = White
        )
    )
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
