package org.example.jetshop.screen

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size

import androidx.compose.runtime.*

import androidx.compose.ui.text.font.FontWeight
import jetshop.composeapp.generated.resources.Res
import jetshop.composeapp.generated.resources.onboard_bg
import jetshop.composeapp.generated.resources.onboard_img

import kotlinx.coroutines.launch
import org.example.jetshop.model.OnboardingPage

import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import org.example.jetshop.ui.theme.BlueDark


import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

object OnBoarding : Screen {
    @Composable
    override fun Content() {
        OnBoardingScreen(onFinish = {})
    }
}
@OptIn(ExperimentalResourceApi::class)
@Composable
fun OnBoardingScreen(
    onFinish: () -> Unit
) {
    val pages = listOf(
        OnboardingPage(
            painterResource(Res.drawable.onboard_bg),
            "Find Food You Love",
            "Discover the best food and drinks around you"
        ),
        OnboardingPage(painterResource(Res.drawable.onboard_img), "Fast Delivery"," Fast Food Delivery at your doorstep"),
    )

    val pagerState = rememberPagerState(initialPage = 0) { pages.size }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White).statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Skip button
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.End
        ) {
            if (pagerState.currentPage < pages.lastIndex) {
                Text(
                    text = "Skip",
                    color = Color.Gray,
                    fontSize = 16.sp,
                    modifier = Modifier.clickable { onFinish() }
                )
            }
        }

        // Pager with image and title
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { pageIndex ->
            val page = pages[pageIndex]
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {


                Image(
                    painter = page.image,
                    contentDescription = null,
                    modifier = Modifier.size(300.dp)
                )


                Spacer(modifier = Modifier.height(20.dp))

                // Pager indicator
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth().padding(16.dp)
                ) {
                    repeat(pages.size) { index ->
                        Box(
                            modifier = Modifier
                                .size(10.dp)
                                .background(
                                    if (pagerState.currentPage == index) Color(0xFFFC6011) else Color.LightGray,
                                    shape = CircleShape
                                )
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))


                Text(
                    text = page.title,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = page.desc,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal
                )
            }
        }



        // Next / Get Started button
        Button(
            onClick = {
                if (pagerState.currentPage == pages.lastIndex) {
                    onFinish()
                } else {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = BlueDark, // Orange background
                contentColor = Color.White // Text color
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(50.dp)
        ) {
            Text(
                text = if (pagerState.currentPage == pages.lastIndex) "Get Started" else "Next"
            )
        }

    }
}