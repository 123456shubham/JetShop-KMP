package org.example.jetshop.screen.home.category

import androidx.compose.runtime.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil3.compose.AsyncImage
import jetshop.composeapp.generated.resources.Res
import jetshop.composeapp.generated.resources.add_to_cart
import jetshop.composeapp.generated.resources.minus
import jetshop.composeapp.generated.resources.plus
import org.example.jetshop.HideBottomBar
import org.example.jetshop.components.CenteredCircularProgressIndicator
import org.example.jetshop.components.LoginPromptDialog
import org.example.jetshop.components.Montserrat
import org.example.jetshop.components.ToolbarWithBackButtonAndTitle
import org.example.jetshop.model.category.CategoryDetailsResponse
import org.example.jetshop.model.category.Product
import org.example.jetshop.model.home.Brand
import org.example.jetshop.model.home.Category
import org.example.jetshop.remote.ApiResponse
import org.example.jetshop.repo.home.HomeRepo
import org.example.jetshop.screen.home.product.ProductDetailsScreen
import org.example.jetshop.screen.login.Login
import org.example.jetshop.ui.theme.ProductTypography
import org.example.jetshop.viewModel.HomeViewModel
import org.jetbrains.compose.resources.painterResource

// ✅ Entry point
data class CategoryBrandScreen(
    val isCategoryScreen: Boolean,
    val categories: List<Any>?
) : Screen, HideBottomBar {
    @Composable
    override fun Content() {
        val viewModel: HomeViewModel = rememberScreenModel { HomeViewModel(HomeRepo()) }
        LeftRightItem(isCategoryScreen, categories, viewModel)
    }
}

@Composable
fun LeftRightItem(
    isCategoryScreen: Boolean,
    items: List<Any>?,
    viewModel: HomeViewModel
) {
    // ✅ Hold selectedId in state (default = first item if exists)
    val navigator=LocalNavigator.currentOrThrow
    val selectedId = remember {
        mutableStateOf(
            when (val first = items?.firstOrNull()) {
                is Category -> first.id.orEmpty()
                is Brand -> first.brand_id.orEmpty()
                else -> ""
            }
        )
    }

    Scaffold(
        topBar = {
            ToolbarWithBackButtonAndTitle(
                title = if (isCategoryScreen) "Shop CategoryWise" else "Shop BrandWise",
                onBackClick = { navigator.pop()}
            )
        }
    ) { padding ->

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            // ✅ Left List
            LeftSideItem(
                items = items,
                selectedId = selectedId.value,
                onItemSelected = { newId ->
                    selectedId.value = newId
                    if (isCategoryScreen) {
                        viewModel.fetchCategoryDetails(newId)
                    } else {
                        viewModel.fetchBrandDetails(newId)
                    }
                },
                modifier = Modifier.weight(1f)
            )

            // ✅ Right List
            RightSideItem(
                categoryId = selectedId.value,
                viewModel = viewModel,
                type = if (isCategoryScreen) "Category" else "Brand",
                modifier = Modifier.weight(3.5f)
            )
        }
    }
}

@Composable
fun LeftSideItem(
    items: List<Any>?,
    selectedId: String,
    onItemSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .width(90.dp)
            .fillMaxHeight()
            .background(Color.White)
    ) {
        items(items.orEmpty()) { item ->
            val itemId = when (item) {
                is Category -> item.id.orEmpty()
                is Brand -> item.brand_id.orEmpty()
                else -> ""
            }

            val itemName = when (item) {
                is Category -> item.name ?: "Unknown Category"
                is Brand -> item.brand_name ?: "Unknown Brand"
                else -> "Unknown"
            }

            val itemLogo = when (item) {
                is Category -> item.icon.orEmpty()
                is Brand -> item.brand_logo.orEmpty()
                else -> ""
            }

            val isSelected = selectedId == itemId

            Card(
                onClick = { onItemSelected(itemId) },
                shape = RoundedCornerShape(8.dp),
                border = if (isSelected) BorderStroke(1.5.dp, Color(0xffF8A44C)) else null,
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 6.dp)
                    .fillMaxWidth()
                    .height(80.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Transparent)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            if (isSelected)
                                Brush.verticalGradient(
                                    listOf(
                                        Color(0xffF7A593).copy(alpha = 0.1f),
                                        Color(0xffF8A44C).copy(alpha = 0.7f)
                                    )
                                )
                            else
                                Brush.verticalGradient(listOf(Color.White, Color.White))
                        )
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    if (itemLogo.isNotEmpty()) {
                        AsyncImage(
                            model = itemLogo,
                            contentDescription = itemName,
                            modifier = Modifier
                                .size(40.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = itemName,
                        fontSize = 12.sp,
                        fontWeight = if (isSelected) FontWeight.Medium else FontWeight.Normal,
                        fontFamily = Montserrat,
                        color = Color.Black,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
fun RightSideItem(
    categoryId: String,
    viewModel: HomeViewModel,
    type: String,
    modifier: Modifier = Modifier
) {
    // ✅ Trigger API whenever categoryId changes
    LaunchedEffect(categoryId, type) {
        if (categoryId.isNotEmpty()) {
            if (type == "Brand") {
                viewModel.fetchBrandDetails(categoryId)
            } else  if (type=="Category"){
                viewModel.fetchCategoryDetails(categoryId)
            }
        }
    }

    val categoryState by viewModel.categoryDetails.collectAsState()
    val brandState by viewModel.brandDetails.collectAsState()
    val navigator = LocalNavigator.currentOrThrow
    val showLoginDialog = remember { mutableStateOf(false) }

    Box(modifier = modifier.fillMaxSize().background(Color.White)) {
        LoginPromptDialog(
            showDialog = showLoginDialog.value,
            onDismiss = { showLoginDialog.value = false },
            onLoginClick = {
                showLoginDialog.value = false
                navigator.push(Login)
            }
        )

        if (type == "Brand") {
            when (brandState) {
                is ApiResponse.Idle -> {}
                is ApiResponse.Loading -> CenteredCircularProgressIndicator()
                is ApiResponse.Success -> {
                    val products =
                        (brandState as ApiResponse.Success<CategoryDetailsResponse>).data.products

                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(5.dp),
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalArrangement = Arrangement.spacedBy(5.dp),
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White)
                            .padding(5.dp)
                    ) {
                        items(products?.size ?: 0) { index ->
                            val product = products?.get(index)
                            CategoryProductCardPage(
                                product = product,
                                onClick = {
                                    navigator.push(ProductDetailsScreen(product?.product_id.toString()))
                                }
                            )
                        }
                    }
                }
                is ApiResponse.Error -> {
                    Text(
                        text = "Error: ${(brandState as ApiResponse.Error).message}",
                        color = Color.Red,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        } else {
            when (categoryState) {
                is ApiResponse.Idle -> {}
                is ApiResponse.Loading -> CenteredCircularProgressIndicator()
                is ApiResponse.Success -> {
                    val products =
                        (categoryState as ApiResponse.Success<CategoryDetailsResponse>).data.products

                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(5.dp),
//                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalArrangement = Arrangement.spacedBy(5.dp),
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White)
                            .padding(5.dp)
                    ) {
                        items(products?.size ?: 0) { index ->
                            val product = products?.get(index)
                            CategoryProductCardPage(
                                product = product,
                                onClick = {
                                    navigator.push(ProductDetailsScreen(product?.product_id.toString()))
                                }
                            )
                        }
                    }
                }
                is ApiResponse.Error -> {
                    Text(
                        text = "Error: ${(categoryState as ApiResponse.Error).message}",
                        color = Color.Red,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun CategoryProductCardPage(
    product: Product?,
    onClick: () -> Unit,
    productQuantity: Int = 0,
    onAddToCart: (String) -> Unit = {},
    onRemoveFromCart: (String) -> Unit = {},
    onUpdateQuantity: (String, Int) -> Unit = { _, _ -> }
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(5.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Column(modifier = Modifier.padding(5.dp)) {
            // ✅ Product Image
            AsyncImage(
                model = product?.product_image_url,
                contentDescription = product?.product_name,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(80.dp)
                    .align(Alignment.CenterHorizontally)
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.height(8.dp))

            // ✅ Product Name
            Text(
                text = product?.product_name.orEmpty(),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(4.dp))

            // ✅ Price row
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "₹ ${product?.product_discount_price}",
                    fontWeight = FontWeight.Bold,
                    style = ProductTypography().prodPriceBold,
                    fontSize = 9.sp,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "₹ ${product?.product_price}",
                    color = Color.Red,
                    fontSize = 9.sp,
                    style = ProductTypography().prodDiscountPrice,
                    textDecoration = TextDecoration.LineThrough,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // ✅ Cart controls
            if (productQuantity > 0) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(35.dp)
                        .border(1.dp, Color(0xFF00C853), RoundedCornerShape(6.dp)),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {
                        val newQty = (productQuantity - 1).coerceAtLeast(0)
                        if (newQty == 0) {
                            onRemoveFromCart(product?.product_id.orEmpty())
                        } else {
                            onUpdateQuantity(product?.product_id.orEmpty(), newQty)
                        }
                    }) {
                        Image(
                            painter = painterResource(Res.drawable.minus),
                            contentDescription = "Minus",
                            modifier = Modifier.size(16.dp)
                        )
                    }

                    Text(
                        text = productQuantity.toString(),
                        style = ProductTypography().productQuantity
                    )

                    IconButton(onClick = {
                        onUpdateQuantity(product?.product_id.orEmpty(), productQuantity + 1)
                    }) {
                        Image(
                            painter = painterResource(Res.drawable.plus),
                            contentDescription = "Add",
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            } else {
                Button(
                    onClick = { onAddToCart(product?.product_id.orEmpty()) },
                    shape = RoundedCornerShape(6.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(36.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF00C853),
                        contentColor = Color.White
                    )
                ) {
                    Image(
                        painter = painterResource(Res.drawable.add_to_cart),
                        contentDescription = "Add to Cart",
                        modifier = Modifier.size(22.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Add to Cart")
                }
            }
        }
    }
}
