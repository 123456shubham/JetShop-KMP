package org.example.jetshop.screen.home.product

import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import cafe.adriel.voyager.core.screen.Screen
import org.example.jetshop.ui.theme.white
import androidx.lifecycle.LifecycleOwner
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.DefaultLifecycleObserver
import coil3.compose.AsyncImage
import jetshop.composeapp.generated.resources.Res
import jetshop.composeapp.generated.resources.border_star
import jetshop.composeapp.generated.resources.delete
import jetshop.composeapp.generated.resources.heart
import jetshop.composeapp.generated.resources.left
import jetshop.composeapp.generated.resources.love
import jetshop.composeapp.generated.resources.minus
import jetshop.composeapp.generated.resources.plus
import jetshop.composeapp.generated.resources.rating
import jetshop.composeapp.generated.resources.star
import org.example.jetshop.components.CenteredCircularProgressIndicator
import org.example.jetshop.components.CustomOutlinedTextField
import org.example.jetshop.components.LoginPromptDialog
import org.example.jetshop.components.Montserrat
import org.example.jetshop.model.productDetails.Product
import org.example.jetshop.model.productDetails.ProductDetailsResponse
import org.example.jetshop.model.productDetails.Review
import org.example.jetshop.model.productDetails.ReviewsData
import org.example.jetshop.remote.ApiResponse
import org.example.jetshop.ui.theme.ProductTypography
import org.example.jetshop.ui.theme.appMainTypography
import org.example.jetshop.utils.Constants.Companion.Ruppes
import org.example.jetshop.viewModel.HomeViewModel
import org.jetbrains.compose.resources.painterResource
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import cafe.adriel.voyager.core.model.rememberScreenModel
import jetshop.composeapp.generated.resources.right_arrow
import org.example.jetshop.HideBottomBar
import org.example.jetshop.repo.home.HomeRepo
import org.example.jetshop.ui.theme.BlueDark


data class ProductDetailsScreen(val productId: String) : Screen, HideBottomBar {
    @Composable
    override fun Content() {
        val viewModel: HomeViewModel = rememberScreenModel { HomeViewModel(HomeRepo()) }


            Box(modifier = Modifier.fillMaxSize().background(white)){
                LaunchedEffect(Unit) {
//        isLoggedIn.value = dataStoreHelper.isUserLoggedIn(context)
                    viewModel.fetchProductDetails( productId)
                }
                ProductDetailScreen(productId,viewModel)
            }


    }
}


@Composable
fun ProductDetailScreen(
    productId:String,
    viewModel: HomeViewModel = HomeViewModel(HomeRepo()),
//    wishlistViewModel: WishlistViewModel = hiltViewModel(),
) {
    //  val productId = backStackEntry.arguments?.getString("product_id") ?: ""

//    val dataStoreHelper = remember { UserDataStore(context) } // Instantiate DataStoreHelper
//    val user by dataStoreHelper.getUserData.collectAsState(initial = null)
    val productDetailsState by viewModel.productDetails.collectAsState()
    val isLoggedIn = remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
//        isLoggedIn.value = dataStoreHelper.isUserLoggedIn(context)
        viewModel.fetchProductDetails( productId)
    }
//    Log.d("TAG_productId", "ProductDetailScreen: $productId")
//    ChangeStatusBarColor(Color(0xfff2f3f2), isDarkIcons = true) // Change to Purple

// Observe lifecycle events
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(lifecycleOwner) {
        lifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onDestroy(owner: LifecycleOwner) {
                super.onDestroy(owner)
//                Log.d("ProductDetails", "onDestroy() called")

            }

            override fun onCreate(owner: LifecycleOwner) {
                super.onCreate(owner)
//                Log.d("ProductDetails", "onCreate() called")
            }

            override fun onStart(owner: LifecycleOwner) {
//                Log.d("ProductDetails", "onStart() called")
            }

            override fun onResume(owner: LifecycleOwner) {
//                Log.d("ProductDetails", "onResume() called")
            }

            override fun onPause(owner: LifecycleOwner) {
//                Log.d("ProductDetails", "onPause() called")
            }

            override fun onStop(owner: LifecycleOwner) {
//                Log.d("ProductDetails", "onStop() called")
            }
        })
    }

//    CallApiForProductDetails(user, dataStoreHelper, productId, viewModel)
    SideEffect {
//        Log.d("ProductDetails", "UI Recomposition occurred")
    }

    when (productDetailsState) {
        is ApiResponse.Success ->{
            val apiData = (productDetailsState as ApiResponse.Success<ProductDetailsResponse?>).data
//            Log.d("ProductDetails", "Product data loaded successfully")
            SetDataUI(apiData, viewModel, isLoggedIn)
        }


        is ApiResponse.Error -> {
            val errorMessage = (productDetailsState as ApiResponse.Error).message
//            Log.e("ProductDetails", "Error loading product: $errorMessage")
            print("ProductDetails ${errorMessage}")
//            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
        }

        is ApiResponse.Loading -> {
//            Log.d("ProductDetails", "Loading product details...")
            CenteredCircularProgressIndicator()
        }

        else -> {
//            Log.w("ProductDetails", "Unexpected state encountered")
            print("ProductDetails Unexpected state encountered")

        }
    }

    // Cleanup when composable is removed
    DisposableEffect(Unit) {
        onDispose {
//            Log.d("ProductDetails", "Composable Disposed")
        }
    }

}

@Composable
fun CallApiForProductDetails(
//    user: UserData?,
//    dataStoreHelper: UserDataStore,
    productId: String,
    viewModel: HomeViewModel
) {
    // Collecting the addToCartResponse state from the ViewModel
//    val addToCartResponse by viewModel.addToCartResponse.collectAsState()
//    val removeToCartResponse by viewModel.removeToCartResponse.collectAsState()

    // First API Call when the user comes to this page (only once)
    LaunchedEffect(Unit) {

//        viewModel.fetchProductDetails( productId)

//        if (user?.userId.isNullOrEmpty()) {
//            // If user_id is null, fetch the user data from the data store
//            dataStoreHelper.getUserData.collect { userData ->
//                // Log the product details fetching
//                Log.d("ProductDetails", "Fetching product details for Product ID: $productId")
//                // Fetch product details using the user_id from the data store
//                viewModel.getProductDetails(userData?.userId ?: "", productId)
//            }
//        } else {
//            // If user_id is available, fetch the product details directly
//            viewModel.getProductDetails(user?.userId ?: "", productId)
//        }
    }

//    LaunchedEffect(addToCartResponse, removeToCartResponse) {
//        if (addToCartResponse is Resource.Success || removeToCartResponse is Resource.Success) {
//            // Log when either add or remove is successful
//            Log.d(
//                "AddRemoveToCart",
//                "Cart updated successfully, refetching product details for Product ID: $productId"
//            )
//            // Fetch the product details again after a successful add or remove response
//            viewModel.getProductDetails(user?.userId ?: "", productId)
//        }
//    }
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
@Composable
fun SetDataUI(
    apiData: ProductDetailsResponse?,
//    wishlistViewModel: WishlistViewModel,
    viewModel: HomeViewModel,
    isLoggedIn: MutableState<Boolean>,
) {
    val productData = apiData?.product
//    val cartData = apiData.cartData
    val ratingReviewData = apiData?.reviewsData
//    var quantityState = remember { mutableStateOf(cartData?.quantity ?: 0) }

    // Bottom Sheet State for Review Listing
    val reviewSheetState = rememberModalBottomSheetState()
    var showReviewSheet by remember { mutableStateOf(false) }

    // Bottom Sheet State for Writing Review
    val writeReviewSheetState = rememberModalBottomSheetState()
    var showWriteReviewSheet by remember { mutableStateOf(false) }
//    val addReviewResponse by viewModel.addReview.collectAsState()
//    if (addReviewResponse is ApiResponse.Success) {
//        Toast.makeText(LocalContext.current, "Review added successfully", Toast.LENGTH_SHORT)
//            .show()
//    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 30.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            ProductHeader(productData)

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Color.White,
                        shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                    ),
                horizontalAlignment = Alignment.Start,

                ) {
//                ProductDetails(productData, quantityState, wishlistViewModel)
                Spacer(modifier = Modifier.height(20.dp))

                // Rating & Review Section
//                ReviewSection(
//                    isLoggedIn,
//                    productData = productData,
//                    ratingReviewData = ratingReviewData,
//                    onViewAllReviewsClick = {
//                        showReviewSheet = true
//                    },  // ✅ Open Review Listing Bottom Sheet
//                    onWriteReviewClick = {
//                        showWriteReviewSheet = true
//                    } // ✅ Open Write Review Bottom Sheet
//                )

                Spacer(modifier = Modifier.height(100.dp))

                // Related Products Section
                // RelatedProductsSection()
            }
        }

        CheckoutButton(
            isLoggedIn = isLoggedIn,
            modifier = Modifier.align(Alignment.BottomCenter),
//            quantityState = quantityState,
//            addTocart = { viewModel.addToCart(productData.productId, "1") },
//            removeFromCart = { viewModel.removeFromCart(productData.productId) },
//            GoToCart = {
//                navController.navigate(Screen.Main.route) {
//                    popUpTo(Screen.ProductDetails.route) { inclusive = false }
//                    launchSingleTop = true
//                    navController.popBackStack()
//                }
//            }
        )
    }

    // ✅ Bottom Sheet for Review Listing
    if (showReviewSheet) {
        ModalBottomSheet(
            onDismissRequest = { showReviewSheet = false },
            sheetState = reviewSheetState
        ) {
//            ReviewListContent(ratingReviewData)
        }
    }

    // ✅ Bottom Sheet for Writing a Review
    if (showWriteReviewSheet) {
        ModalBottomSheet(
            onDismissRequest = { showWriteReviewSheet = false },
            sheetState = writeReviewSheetState
        ) {
//            WriteReviewContent(
//                onSubmit = { rating, title, description ->
//                    viewModel.AddUpdateReview(
//                        productData.productId,
//                        rating.toFloat(),
//                        title,
//                        description
//                    )
//                    showWriteReviewSheet = false
//                }
//            )
        }
    }
}

@Composable
fun ReviewListContent(reviews: ReviewsData) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            "Reviews", fontWeight = FontWeight.Bold,
            fontFamily = Montserrat, fontSize = 24.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(reviews.reviewList.size) { review ->
                ReviewItem(reviews.reviewList[review])
            }
        }
    }
}

@Composable
fun ReviewItem(review: Review) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // User name with fallback for anonymous
            Text(
                text = review. user_name?: "Anonymous",
                fontSize = 18.sp,
                fontFamily = Montserrat,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            // Rating display with stars (can replace with a custom RatingBar if needed)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Rating:",
                    fontFamily = Montserrat,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(end = 8.dp)
                )
                // Represent the rating visually (stars can be dynamic based on reviewRating)
                RatingStars(rating = review.reviewRating.toFloatOrNull() ?: 0.0f)
            }

            // Review title
            Text(
                text = review.reviewTitle,
                fontFamily = Montserrat,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(vertical = 4.dp)
            )

            // Review comment
            Text(
                text = review.reviewComment,
                fontFamily = Montserrat,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Divider to separate reviews
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
        }
    }
}

// Custom composable for rating stars (for a more professional look)
@Composable
fun RatingStars(rating: Float) {
    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
        // Display full stars
        repeat(rating.toInt()) {
            Image(
                painter = painterResource(Res.drawable.rating),
                contentDescription = "Star",
                modifier = Modifier.size(20.dp)
//                tint = MaterialTheme.colorScheme.primary
            )
        }

        // Display half star if needed
        if (rating % 1 >= 0.5) {
            Image(
                painter = painterResource(Res.drawable.star),
                contentDescription = "Star",
                modifier = Modifier.size(20.dp)
//                tint = MaterialTheme.colorScheme.primary
            )
        }

        // Display empty stars
        repeat(5 - rating.toInt() - if (rating % 1 >= 0.5) 1 else 0) {
//            Icon(
//                imageVector = Icons.Default.StarBorder,
//                contentDescription = "Empty Star",
//                tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
//            )

            Image(
                painter = painterResource(Res.drawable.rating),
                contentDescription = "Star",
                modifier = Modifier.size(20.dp)
//                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}


@Composable
fun WriteReviewContent(onSubmit: (Int, String, String) -> Unit) {
    var rating by remember { mutableStateOf(0) }
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Write a Review",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = Montserrat,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Star Rating
        Row(modifier = Modifier.padding(vertical = 8.dp)) {
            for (i in 1..5) {
                Icon(
                    painter = if (i <= rating)
                        painterResource(Res.drawable.star)
                    else
                        painterResource(Res.drawable.border_star),
                    contentDescription = "$i Star",
                    tint = if (i <= rating) Color(0xFFFFD700) else Color.Gray, // ⭐ gold vs gray
                    modifier = Modifier
                        .size(32.dp)
                        .clickable {
                            rating = i
//                            onRatingChanged(i)
                        }
                )
            }
        }

        CustomOutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = "Title",
//            leadingIcon = Icons.AutoMirrored.Filled.Message,
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Description Input
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 4,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text)
        )

        // Error Message
        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = Color.Red,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Submit Button
        Button(
            onClick = {
                // Validation
                if (rating == 0) {
                    errorMessage = "Rating is required."
                } else if (title.isEmpty()) {
                    errorMessage = "Title is required."
                } else if (description.isEmpty()) {
                    errorMessage = "Description is required."
                } else {
                    errorMessage = "" // Clear the error message
                    onSubmit(rating, title, description)
                }
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("Submit Review")
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}


@Composable
fun ProductHeader(
    productData: Product?,
) {
    //curve for header card
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Color(0xfff2f3f2),
                //  shape = RoundedCornerShape(bottomStartPercent = 17, bottomEndPercent = 17)
            )
    ) {
        Spacer(modifier = Modifier.height(26.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 15.dp, top = 15.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
//                    navController.popBackStack()
                },
                modifier = Modifier
                    .background(color = Color.White, shape = CircleShape)
                    .clip(CircleShape)

            ) {
                Image(painter = painterResource(Res.drawable.left), contentDescription = null)

            }
            Row(
                modifier = Modifier
                    .width(70.dp)
                    .background(color = Color.White, shape = RoundedCornerShape(8.dp))
                    .padding(3.dp)
                    .clip(RoundedCornerShape(8.dp)),
                horizontalArrangement = Arrangement.spacedBy(
                    4.dp,
                    Alignment.CenterHorizontally
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = productData?.product_rating.toString(),
                    style = appMainTypography().subHeader,
                    color = Color.Black
                )
                Image(
                    painter = painterResource(Res.drawable.star),
                    contentDescription = "Rating",
//                    tint = Color(0xFFFFD700), // Align the icon to the center horizontally
                )
            }}

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Top
        ) {
            AsyncImage(
                model = productData?.product_image_url,
                contentDescription = productData?.product_name,
                modifier = Modifier.size(400.dp, 250.dp),
                contentScale = ContentScale.Fit
            )
        }
    }
}

@Composable
fun ProductDetails(
    productData: Product,
    quantityState: MutableState<Int>,
//    wishlistViewModel: WishlistViewModel,
) {
    var isInWishlist by remember { mutableStateOf(false) }

    LaunchedEffect(productData) {
//        wishlistViewModel.isProductInWishlist(productData.productId) {
//            isInWishlist = it
//        }
    }
//    val wishlistProduct = productData.toWishlistProduct()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = productData.product_name.toString(),
                style = ProductTypography().prodTitleMedium,
                modifier = Modifier.weight(1f)
            )
            Column {
                IconButton(
                    onClick = {
                        if (isInWishlist) {
//                            wishlistViewModel.removeFromWishlist(wishlistProduct)
                        } else {
//                            wishlistViewModel.addToWishlist(wishlistProduct)
                        }
                        isInWishlist = !isInWishlist // Toggle UI state
                    }) {
                    Image(
                        modifier = Modifier.size(35.dp),
                        painter =painterResource( if (isInWishlist) Res.drawable.love else Res.drawable.heart),
                        contentDescription = "Favorite",
//                        tint = if (isInWishlist) Color.Red else Color.Gray
                    )
                }
            }

        }
    }

    Spacer(modifier = Modifier.height(10.dp))

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp), horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = Ruppes + productData.product_discount_price,
            style = ProductTypography().prodPriceBold,
        )
        Text(
            text = Ruppes + productData.product_discount_price,
            style = ProductTypography().prodDiscountPrice,
        )
    }
    val loremText =
//        LoremIpsum(50).values // Generates 5 paragraphs of Lorem Ipsum text
    productData.product_description?.let {
        Text(
            text = it,
            style = ProductTypography().prodDescription,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(16.dp)
        )
    }

    /*Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        QuantitySelector(quantityState.value, onQuantityChanged = { quantityState.value = it })
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "Rating",
                tint = Color(0xFFFFD700)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = productData.productRating,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }*/
}

@Composable
fun ReviewSection(
    isLoggedIn: MutableState<Boolean>,
    productData: Product,
    ratingReviewData: ReviewsData,
    onWriteReviewClick: () -> Unit,
    onViewAllReviewsClick: () -> Unit,
) {
//    val totalReviews: Float = ratingReviewData.totalReviewCount?.toFloat()?.coerceAtLeast(1f) // Avoid division by zero

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onViewAllReviewsClick() },
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                "Ratings & Reviews",
                style = appMainTypography().subHeader,
            )
            Icon(
                painter = painterResource(Res.drawable.right_arrow),
                contentDescription = null,
                tint = Color.Blue
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = productData.product_rating.toString(),
                    fontSize = 50.sp,
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "${ratingReviewData.totalReviewCount} reviews",
                    style = appMainTypography().subHeader,
                )
            }
//            Column {
//                // Animated progress values
//                val fiveStarProgress by animateFloatAsState(
//                    targetValue = ratingReviewData.fiveStarReviewCount?.div(totalReviews) ?: 1f,
//                    animationSpec = tween(1000)
//                )
//                val fourStarProgress by animateFloatAsState(
//                    targetValue = ratingReviewData.fourStarReviewCount?.div(totalReviews) ?: 1f,
//                    animationSpec = tween(1000)
//                )
//                val threeStarProgress by animateFloatAsState(
//                    targetValue = ratingReviewData.threeStarReviewCount?.div(totalReviews) ?: 1f,
//                    animationSpec = tween(1000)
//                )
//                val twoStarProgress by animateFloatAsState(
//                    targetValue = ratingReviewData.twoStarReviewCount?.div(totalReviews) ?: 1f,
//                    animationSpec = tween(1000)
//                )
//                val oneStarProgress by animateFloatAsState(
//                    targetValue = ratingReviewData.oneStarReviewCount?.div(totalReviews) ?: 1f,
//                    animationSpec = tween(1000)
//                )
//
//                // Progress bars
//                RatingProgressBar(stars = 5, progress = fiveStarProgress)
//                RatingProgressBar(stars = 4, progress = fourStarProgress)
//                RatingProgressBar(stars = 3, progress = threeStarProgress)
//                RatingProgressBar(stars = 2, progress = twoStarProgress)
//                RatingProgressBar(stars = 1, progress = oneStarProgress)
//            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        if (isLoggedIn.value) {
            Button(
                onClick = onWriteReviewClick,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Write a Review", fontFamily = Montserrat, fontWeight = FontWeight.SemiBold)
            }
        }

    }
}

@Composable
fun RatingProgressBar(stars: Int, progress: Float) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text("$stars★", fontSize = 14.sp, modifier = Modifier.width(24.dp), fontFamily = Montserrat)
        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier
                .width(180.dp)
                .height(8.dp),
        )
    }
}


@Composable
fun RelatedProductsSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text("Related Products", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        LazyRow {
            items(10) { index -> // Mock Data
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .background(Color.Gray)
                        .padding(8.dp)
                ) {
                    Text("Product $index", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun CheckoutButton(
    isLoggedIn: MutableState<Boolean>,
    modifier: Modifier,
//    quantityState: MutableState<Int>,
//    addTocart: () -> Unit,
//    removeFromCart: () -> Unit,
//    GoToCart: () -> Unit,
) {
    val showLoginDialog = remember { mutableStateOf(false) }

// Show the login prompt when needed
    LoginPromptDialog(
        showDialog = showLoginDialog.value,
        onDismiss = { showLoginDialog.value = false },
        onLoginClick = {
            showLoginDialog.value = false
//            navController.navigate(Screen.Login.route)
        }
    )
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp).padding(WindowInsets.navigationBars.asPaddingValues()),
        contentAlignment = Alignment.Center
    ) {
        val quantityState = remember { mutableStateOf(0) }
        if (quantityState.value > 0) {
            // Show two buttons: "Remove from Cart" and "Cart"
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // "Remove from Cart" button
                Button(
                    onClick = {
//                        removeFromCart()
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(55.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE53935))
                ) {
                    Image(
                        painter =painterResource(Res.drawable.delete),
                        contentDescription = "Remove from Cart",
//                        tint = Color.White
                    )
                }

                Spacer(modifier = Modifier.width(8.dp)) // Spacer between buttons

                // "Cart" button
                Button(
                    onClick = {
//                        GoToCart()
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(55.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E88E5))
                ) {
                    Text(
                        "Cart",
                        fontSize = 18.sp,
                        fontFamily = Montserrat,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        } else {
            // Show "Add to Cart" button when quantity is 0
            Button(
                onClick = {
                    if (isLoggedIn.value) {
//                        addTocart()
                    } else {
                        showLoginDialog.value = true
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E88E5))
            ) {
                Text(
                    "Add to Cart",
                    fontSize = 18.sp,
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}


// Quantity Selector Component
@Composable
fun QuantitySelector(quantity: Int, onQuantityChanged: (Int) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { if (quantity > 1) onQuantityChanged(quantity - 1) },
            modifier = Modifier.border(
                shape = RoundedCornerShape(20),
                width = 2.dp,
                color = Color(0xFFB3B3B3)
            )
        ) {
            Icon(painterResource(Res.drawable.minus), contentDescription = "Decrease Quantity")
        }
        Text(
            text = quantity.toString(),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = Montserrat,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        IconButton(
            onClick = { onQuantityChanged(quantity + 1) }, modifier = Modifier.border(
                shape = RoundedCornerShape(20),
                width = 2.dp,
                color = Color(0xFFB3B3B3)
            )
        ) {
            Icon(painterResource(Res.drawable.plus), contentDescription = "Increase Quantity")
        }
    }
}


//@Composable
//fun ChangeStatusBarColor(color: Color, isDarkIcons: Boolean) {
//    val activity = LocalContext.current as Activity
//    val window: Window = activity.window
//    val insetsController = WindowInsetsControllerCompat(window, window.decorView)
//
//    SideEffect {
//        /*  window.statusBarColor = android.graphics.Color.parseColor(color.toString())
//          insetsController.isAppearanceLightStatusBars = isDarkIcons
//  */
//        window.statusBarColor = android.graphics.Color.TRANSPARENT
//        WindowCompat.setDecorFitsSystemWindows(window, false)
//        insetsController.isAppearanceLightNavigationBars = true
//        insetsController.isAppearanceLightStatusBars = isDarkIcons
//
//
//    }
//}