package org.example.jetshop.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned

import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import jetshop.composeapp.generated.resources.Res
import jetshop.composeapp.generated.resources.onboard_img
import org.jetbrains.compose.resources.painterResource

@Composable
fun BlurView() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFAFAFA)), // Light background
        contentAlignment = Alignment.Center
    ) {
        var sizeImage by remember { mutableStateOf(IntSize.Zero) }

        val gradient = Brush.verticalGradient(
            colors = listOf(
                Color.Transparent,
                Color.Black
            ),
            startY = sizeImage.height.toFloat() / 1.1f,
            endY = sizeImage.height.toFloat()
        )

        Box {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .blur(8.dp)
                    .onGloballyPositioned { sizeImage = it.size },
                painter = painterResource( Res.drawable.onboard_img),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(gradient)
            )
        }
    }
}

