package org.example.jetshop.screen.home.banner

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.example.jetshop.components.Spacer_8dp
import org.example.jetshop.model.home.SliderImage
import org.example.jetshop.ui.theme.BackgroundContent
import org.example.jetshop.ui.theme.BlueDark

sealed class ItemType {
    data class Brand(val brandData: org.example.jetshop.model.home.Brand) : ItemType()
    data class Category(val categoryData: org.example.jetshop.model.home.Category) : ItemType()
}

@Composable
fun AutoSlidingBanner(sliderImageData: List<SliderImage>?) {
    if (sliderImageData.isNullOrEmpty()) return // ✅ Exit if null or empty

    val pagerState = rememberPagerState(pageCount = { sliderImageData.size })
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        while (true) {
            delay(4000) // Delay before auto-slide
            coroutineScope.launch {
                val nextPage = (pagerState.currentPage + 1) % pagerState.pageCount
                pagerState.animateScrollToPage(
                    nextPage, animationSpec = tween(800)
                ) // Smooth scroll
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxWidth().padding(top = 30.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer_8dp() // Space between pager & dots
        HorizontalPager(state = pagerState) { page ->
            BannerImage(sliderImageData[page].image_url.toString())
        }

        Spacer_8dp() // Space between pager & dots

        sliderImageData.size.let {
            DotsIndicator(
                totalDots = it, selectedIndex = pagerState.currentPage, dotSize = 8.dp
            )
        }
    }
}


@Composable
fun BannerImage(it: String) {
    Box(modifier = Modifier.padding(horizontal = 8.dp)) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = BackgroundContent, // Set your card background color
                contentColor = Color.White // Set text or icon color inside the card
            ),
            modifier = Modifier
                .height(190.dp)
                .fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(it),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
            )
        }
    }
}


@Composable
fun DotsIndicator(
    modifier: Modifier = Modifier,
    totalDots: Int,
    selectedIndex: Int,
    selectedColor: Color = MaterialTheme.colorScheme.primary,
    unSelectedColor: Color = BlueDark,
    dotSize: Dp = 8.dp,
) {

    Row(
        modifier = modifier,
        verticalAlignment = CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        for (index in 0 until totalDots) {

            val color by remember(selectedIndex) { derivedStateOf { Animatable(unSelectedColor) } }
            val size by animateDpAsState(if (index == selectedIndex) 20.dp else dotSize)


            LaunchedEffect(selectedIndex) {


                color.animateTo(
                    if (index == selectedIndex) selectedColor else unSelectedColor,
                    animationSpec = tween(300)
                )

            }

            Row(
                modifier = Modifier,
                verticalAlignment = CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Dot(size = size, color = color.value, shape = RoundedCornerShape(16.dp))
            }
        }
    }


}

@Composable
private fun Dot(
    modifier: Modifier = Modifier,
    shape: Shape = CircleShape,
    size: Dp = 8.dp,
    color: Color,
) {
    Box(
        modifier = modifier
            .padding(horizontal = 3.dp)
            .height(8.dp)
            .width(size)
            .clip(shape)
            .background(color)
    )
}