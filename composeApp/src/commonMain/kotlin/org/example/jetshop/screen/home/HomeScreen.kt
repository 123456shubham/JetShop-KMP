package org.example.jetshop.screen.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.crossfade
import jetshop.composeapp.generated.resources.Res
import jetshop.composeapp.generated.resources.logo_trans
import jetshop.composeapp.generated.resources.love
import jetshop.composeapp.generated.resources.premium
import jetshop.composeapp.generated.resources.search
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.example.jetshop.bottomNavigation.LocalShowBottomBar
import org.example.jetshop.components.CenteredCircularProgressIndicator
import org.example.jetshop.components.Spacer_12dp
import org.example.jetshop.components.Spacer_8dp
import org.example.jetshop.components.TitleSmall
import org.example.jetshop.model.home.Brand
import org.example.jetshop.model.home.Category
import org.example.jetshop.model.home.HomeResponse
import org.example.jetshop.model.home.Product
import org.example.jetshop.model.home.SliderImage
import org.example.jetshop.remote.ApiResponse
import org.example.jetshop.repo.auth.RegisterRepo
import org.example.jetshop.repo.home.HomeRepo
import org.example.jetshop.screen.home.banner.AutoSlidingBanner
import org.example.jetshop.screen.home.brand.BrandListing
import org.example.jetshop.screen.home.category.CategoriesListing
import org.example.jetshop.screen.home.product.ProductListing
import org.example.jetshop.ui.theme.DarkBlue
import org.example.jetshop.ui.theme.appMainTypography
import org.example.jetshop.ui.theme.white
import org.example.jetshop.viewModel.AuthViewModel
import org.example.jetshop.viewModel.HomeViewModel
import org.jetbrains.compose.resources.painterResource

object HomeScreen : Screen {
    @Composable
    override fun Content() {

        val navigator= LocalNavigator.currentOrThrow
        val viewModel: HomeViewModel = rememberScreenModel { HomeViewModel(HomeRepo()) }
//        val userName by viewModel.userName.collectAsState()
//        val userIsPrimeActive by viewModel.userIsPrimeActive.collectAsState()



        // Bottom bar visibility callback
//        val LocalShowBottomBar = staticCompositionLocalOf<(Boolean) -> Unit> { {} }

        val updateBottomBar = LocalShowBottomBar.current

        // Scroll state for LazyColumn
        val listState = rememberLazyListState()
        var lastIndex by remember { mutableStateOf(0) }

        // Detect scroll up/down
        LaunchedEffect(listState) {
            snapshotFlow { listState.firstVisibleItemIndex }
                .collect { index ->
                    if (index > lastIndex) {
                        updateBottomBar(false) // scrolling down → hide
                    } else {
                        updateBottomBar(true) // scrolling up → show
                    }
                    lastIndex = index
                }
        }

        val userName="Shubham Chauhan"
        Scaffold(
            topBar = {
                userName.let {
                    MyTopAppBar(
                        it,
//                        userIsPrimeActive,
//                        onSearchClick = { navController.navigate(Screen.SearchScreen.route) },
                        onSearchClick = {  },
                        onClickPrime = {

//                            val intent =
//                                Intent(context, PrimePayScreen::class.java).apply {
//                                    putExtra("user_id", userId)
//                                    putExtra("appName", "JetShop")
//                                    putExtra("email", email)
//                                    putExtra("contact", contact)
//                                }
//                            // context.startActivity(intent)
//                            context.startActivity(intent)
                        },
                    )
                }
            },
        ){ paddingValues ->
            Box(modifier = Modifier.fillMaxSize().background(white).padding(top = 40.dp)){

                val homeState by viewModel.homeState.collectAsState()
//            val isLoggedIn by rememberSaveable { mutableStateOf(false) }
                val isLoggedIn = remember { mutableStateOf(false) } // 👈 MutableState<Boolean>


                LaunchedEffect(Unit){
                    viewModel.fetchHome()
                }
                when (homeState) {
                    is ApiResponse.Idle -> {}
                    is ApiResponse.Loading -> {
                        CenteredCircularProgressIndicator()
                    }
                    is ApiResponse.Success -> {
                        // TODO
                        val data = (homeState as ApiResponse.Success<HomeResponse>).data
                        LazyColumn {
                            item {
                                AutoSlidingBanner(data.slider_images as List<SliderImage>?)
                            }
                            item {
                                CategoriesListing(data.categories as List<Category>)
                            }

                            item {
                                BrandListing(data.brands as List<Brand>)
                            }

                            data?.sections?.forEach { section ->
                                item {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = 16.dp, vertical = 8.dp),
                                        verticalAlignment = CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        section?.section_name?.let {
                                            Text(
                                                it,
                                                style = appMainTypography().subHeader
                                            )
                                        }
                                        Text(
                                            "see all",
                                            style = appMainTypography().seeAllText,
                                            color = MaterialTheme.colorScheme.primary,
                                            modifier = Modifier.clickable {
//                                            navController.navigate(Screen.AllProductList.route)
                                            })
                                    }
                                }

                                item {
                                    ProductListing(section?.products as List<Product>,viewModel,isLoggedIn )
                                }
                            }


                        }
                    }
                    is ApiResponse.Error -> {}
                }
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(
    name: String,
//    userIsPrimeActive: String?,
    onSearchClick: () -> Unit,
    onClickPrime: () -> Unit
) {
    val tooltipState = rememberTooltipState()
    val scope = rememberCoroutineScope()
    val userIsPrimeActive="1"
    Column(
        Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding( start = 10.dp, end = 10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Left side: Greeting
            AnimatedGreeting(name, true)

            // Right side: Prime or Not + Search
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (userIsPrimeActive == "1") {
                    // ✅ Use TooltipBox from Material3
                    TooltipBox(
                        positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(),
                        tooltip = {
                            PlainTooltip { Text("You're a Prime Member!",style = appMainTypography().seeAllText,) }
                        },
                        state = tooltipState
                    ) {
                        Image(
                            painter = painterResource( Res.drawable.premium),
                            contentDescription = "Prime Member",
                            modifier = Modifier
                                .size(40.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .clickable {
                                    scope.launch {
                                        tooltipState.show()
                                    }
                                }
                        )
                    }
                } else {
                    // ✅ Show upgrade button
                    ShimmerPrimeBadge { onClickPrime() }
                }

                Spacer_12dp()

                IconButton(onClick = onSearchClick) {
                    Image(
                        modifier = Modifier
                            .size(55.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(white)
                            .padding(8.dp),
                        painter = painterResource(Res.drawable.search),
                        contentDescription = null,

//                        colorFilter =  DarkBlue
                    )
                }
            }
        }

    }
}


@Composable
fun ShimmerPrimeBadge(onClick: () -> Unit) {
    val shimmerColors = listOf(
        Color.White.copy(alpha = 0.6f),
        Color.White.copy(alpha = 0.9f),
        Color.White.copy(alpha = 0.6f)
    )

    val transition = rememberInfiniteTransition()
    val translateAnim by transition.animateFloat(
        initialValue = 0f,
        targetValue = 300f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    val shimmerBrush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,
        end = Offset(x = translateAnim, y = 0f)
    )

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(18.dp))
            .background(Color(0xFF03A9F4)) // Solid sky blue background
            .clickable {
//                Log.d("ShimmerPrimeBadge", "✅ Prime clicked")
                onClick()
            }
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = "Prime",
            style = TextStyle(
                brush = shimmerBrush,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        )
    }
}


@Composable
fun AnimatedGreeting(name: String, moveUp: Boolean) {
    val greetings = listOf(
        "Hi, $name", "JetShop"
    )

    var currentIndex by remember { mutableStateOf(0) }

    // Determine slide direction
    val slideDirection = if (moveUp) {
        AnimatedContentTransitionScope.SlideDirection.Up
    } else {
        AnimatedContentTransitionScope.SlideDirection.Down
    }

    // Start the animation loop
    LaunchedEffect(Unit) {
        while (true) {
            delay(5000) // Show the greeting for a while before changing
            currentIndex = (currentIndex + 1) % greetings.size
        }
    }

    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
    ) {
//        Image(painter = rememberAsyncImagePainter(ImageRequest.Builder(LocalContext.current)
//            .data(data = Res.drawable.logo_trans).apply { crossfade(true) }
//            .build()),
//            modifier = Modifier
//                .clip(CircleShape)
//                .size(35.dp),
//            contentDescription = null,
//            contentScale = ContentScale.Crop)

        Spacer_8dp()
        val animationTime = 800

        AnimatedContent(
            targetState = currentIndex, transitionSpec = {
                slideIntoContainer(slideDirection, tween(animationTime)) + fadeIn(
//                    animationSpec = tween(animationTime)
                ) togetherWith slideOutOfContainer(slideDirection, tween(animationTime)) + fadeOut(
//                    animationSpec = tween(animationTime)
                )
            }, label = ""
        ) { index ->
            Text(
                greetings[index],
                style = appMainTypography().sectionHeader,
            )
        }
    }
}

/*
* https://github.com/pedrotlf/JetpackComposeAnimationSample?tab=readme-ov-file*/
@Composable
fun AnimatedGreeting(name: String) {
    val greetings = listOf(
        "Hi, $name", "JetShop"
    )

    var currentIndex by remember { mutableStateOf(0) }
    val offsetY = remember { androidx.compose.animation.core.Animatable(0f) }

    // Start the animation
    LaunchedEffect(Unit) {
        while (true) {
            // Animate up
            offsetY.animateTo(-50f, animationSpec = tween(durationMillis = 500))
            currentIndex = (currentIndex + 1) % greetings.size

            // Animate down
            offsetY.animateTo(0f, animationSpec = tween(durationMillis = 500))
            delay(5000) // Show the greeting for a while before changing
        }
    }

    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.offset(y = offsetY.value.dp) // Convert Float to Dp
    ) {
//        Image(
//            painter = rememberAsyncImagePainter(
//                ImageRequest.Builder(LocalContext.current)
//                    .data(data = R.drawable.logo_trans)
//                    .apply(block = fun ImageRequest.Builder.() {
//                        crossfade(true)
//                    }).build()
//            ),
//            modifier = Modifier
//                .clip(CircleShape)
//                .size(35.dp),
//            contentDescription = null,
//            contentScale = ContentScale.Crop
//        )
        Spacer_8dp()
        TitleSmall(text = greetings[currentIndex])
    }
}

