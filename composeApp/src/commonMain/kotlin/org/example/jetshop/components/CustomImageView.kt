package org.example.jetshop.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.example.jetshop.utils.CommonFunction.GradientLoader
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun CircularImage(
    imageRes: DrawableResource,
    size: Dp = 100.dp,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource( imageRes),
        contentDescription = "Circular Image",
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .border(2.dp, Color.Gray, CircleShape)
    )
}

@Composable
fun RoundedSquareImage(
    imageRes: DrawableResource,
    size: Dp = 100.dp,
    cornerRadius: Dp = 16.dp,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(imageRes),
        contentDescription = "Rounded Square Image",
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(size)
            .clip(RoundedCornerShape(cornerRadius))
            .border(2.dp, Color.Gray, RoundedCornerShape(cornerRadius))
    )
}
@Composable
fun CenteredCircularProgressIndicator() {
   /* Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(50.dp) // Set the size of the CircularProgressIndicator
                .align(Alignment.Center) // Align it to the center of the Box
        )
    }*/
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
       GradientLoader()
    }
}