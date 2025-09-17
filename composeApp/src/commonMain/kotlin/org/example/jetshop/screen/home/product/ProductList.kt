package org.example.jetshop.screen.home.product

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
//import androidx.paging.compose.collectAsLazyPagingItems
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
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
import org.example.jetshop.components.Spacer_4dp
import org.example.jetshop.components.Spacer_8dp
import org.example.jetshop.components.ToolbarWithBackButtonAndTitle
import org.example.jetshop.model.home.Product
import org.example.jetshop.model.productDetails.productList.ProductResponse
import org.example.jetshop.remote.ApiResponse
import org.example.jetshop.repo.home.HomeRepo
import org.example.jetshop.ui.theme.ProductTypography
import org.example.jetshop.ui.theme.white
import org.example.jetshop.viewModel.HomeViewModel
import org.jetbrains.compose.resources.painterResource


data class ProductList(val productsTitle: String,
                       val products: List<Product>
) : Screen, HideBottomBar{
    @Composable
    override fun Content() {
        val viewModel: HomeViewModel = rememberScreenModel { HomeViewModel(HomeRepo()) }

        Box(modifier = Modifier.fillMaxSize().background(white)){
            ProductListScreen(productsTitle,viewModel,products)

        }
    }
}

@Composable
fun ProductListScreen(

    productsTitle: String,
    viewModel: HomeViewModel ,
    products: List<Product>


//    wishlistViewModel: WishlistViewModel = hiltViewModel(),
) {
//    val context = LocalContext.current
//    val dataStoreHelper = remember { UserDataStore(context) }
    val isLoggedIn = remember { mutableStateOf(false) }
    val showLoginDialog = remember { mutableStateOf(false) }

    val navigator=LocalNavigator.currentOrThrow
    LaunchedEffect(Unit) {
//        viewModel.productList()
//        isLoggedIn.value = dataStoreHelper.isUserLoggedIn(context)
    }

    LoginPromptDialog(
        showDialog = showLoginDialog.value,
        onDismiss = { showLoginDialog.value = false },
        onLoginClick = {
            showLoginDialog.value = false
//            navController.navigate(Screen.Login.route)
        }
    )
//    val productState by  viewModel.productList.collectAsState()

    Scaffold(topBar = {
        ToolbarWithBackButtonAndTitle(title = "${productsTitle}",
            onBackClick = {
            navigator.pop()
        })
    }) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize().background(white)) { // Fix: Remove padding here


            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues) // Fix: Apply padding only here
            ) {
                items(products.size) { index ->
//
                    val product = products?.get(index)

                    var isInWishlist by remember { mutableStateOf(false) }

//                            LaunchedEffect(product) {
//                                wishlistViewModel.isProductInWishlist(product.productId) {
//                                    isInWishlist = it
//                                }
//                            }
//                            val wishlistProduct = product.toWishlistProduct()

                    ProductCardPage(
//                                viewModel = viewModel,
                        product = product,
                        onClick = {navigator.push(ProductDetailsScreen(product?.product_id.toString()))}
//                                onAddToCart = { productId ->
//                                    if (isLoggedIn.value) {
//                                        viewModel.addToCart(productId, "1")
//                                    } else {
//                                        showLoginDialog.value = true
//                                    }
//                                },
//                                onViewProduct = { selectedProduct ->
//                                    navController.navigate("${Screen.ProductDetails.route}/${selectedProduct.productId}")
//                                },
//                                onWishlistToggle = {
//                                    if (isInWishlist) {
//                                        wishlistViewModel.removeFromWishlist(wishlistProduct)
//                                    } else {
//                                        wishlistViewModel.addToWishlist(wishlistProduct)
//                                    }
//                                    isInWishlist = !isInWishlist
//                                },
//                                isInWishlist = isInWishlist,
//                                onRemoveFromCart = { productId ->
//                                    viewModel.removeFromCart(productId)
//                                }
                    )

                }
            }
//            when (productState) {
//                is ApiResponse.Idle -> {}
//                is ApiResponse.Loading -> {
//                    CenteredCircularProgressIndicator()
//                }
//                is ApiResponse.Success -> {
//                    // TODO
//                    val productData = (productState as ApiResponse.Success<ProductResponse>).data
//
//
//                    LazyVerticalGrid(
//                        columns = GridCells.Fixed(2),
//                        contentPadding = PaddingValues(8.dp),
//                        horizontalArrangement = Arrangement.spacedBy(8.dp),
//                        verticalArrangement = Arrangement.spacedBy(8.dp),
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .padding(paddingValues) // Fix: Apply padding only here
//                    ) {
//                        items(productData.products?.size ?: 0) { index ->
////
//                            val product = productData.products?.get(index)
//
//                            var isInWishlist by remember { mutableStateOf(false) }
//
////                            LaunchedEffect(product) {
////                                wishlistViewModel.isProductInWishlist(product.productId) {
////                                    isInWishlist = it
////                                }
////                            }
////                            val wishlistProduct = product.toWishlistProduct()
//
//                            ProductCardPage(
////                                viewModel = viewModel,
//                                product = product,
//                                onClick = {navigator.push(ProductDetailsScreen(product?.product_id.toString()))}
////                                onAddToCart = { productId ->
////                                    if (isLoggedIn.value) {
////                                        viewModel.addToCart(productId, "1")
////                                    } else {
////                                        showLoginDialog.value = true
////                                    }
////                                },
////                                onViewProduct = { selectedProduct ->
////                                    navController.navigate("${Screen.ProductDetails.route}/${selectedProduct.productId}")
////                                },
////                                onWishlistToggle = {
////                                    if (isInWishlist) {
////                                        wishlistViewModel.removeFromWishlist(wishlistProduct)
////                                    } else {
////                                        wishlistViewModel.addToWishlist(wishlistProduct)
////                                    }
////                                    isInWishlist = !isInWishlist
////                                },
////                                isInWishlist = isInWishlist,
////                                onRemoveFromCart = { productId ->
////                                    viewModel.removeFromCart(productId)
////                                }
//                            )
//
//                        }
//                    }
//                }
//                is ApiResponse.Error -> {
//
//                    val error = (productState as ApiResponse.Error).message
//                    Box(
//                        modifier = Modifier.fillMaxSize(),
//                        contentAlignment = Alignment.Center
//                    ) {
//                        Column(
//                            horizontalAlignment = Alignment.CenterHorizontally
//                        ) {
//                            Text(text = "Error: ${error}", color = Color.Red, fontFamily = Montserrat)
//                            Spacer_8dp()
//                            Button(onClick = { viewModel.productList() }) {
//                                Text("Retry",fontFamily = Montserrat)
//                            }
//                        }
//                    }
//                }
//
//                }
            }
        }


}


@Composable
fun ProductCardPage(
    product: Product?,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(5.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        colors = CardDefaults.cardColors(white)
    ) {
        Column (
            modifier = Modifier.padding(8.dp)
        ) {
            AsyncImage(
                model = product?.product_image_url,
                contentDescription = product?.product_name,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(80.dp)
                    .align(Alignment.CenterHorizontally)
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer_8dp()
            Text(product?.product_name.toString(), maxLines = 1, fontWeight = FontWeight.Bold, fontSize = 16.sp)


            Row(modifier = Modifier.fillMaxWidth()) {
                Text("₹ "+product?.product_discount_price.toString(),
                    fontWeight = FontWeight.Bold,
                    style = ProductTypography().prodPriceBold,

                    fontSize = 12.sp, modifier = Modifier.weight(1f))
//                Spacer_4dp()
                Text("₹ ${product?.product_price}",
                    color = Color.Red,
                    fontSize = 12.sp,
                    style = ProductTypography().prodDiscountPrice,
                    modifier = Modifier.weight(1f))
            }

            Spacer_8dp()

            val productQuantity=0
            // Quantity Selector UI
            if ( productQuantity > 0) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(35.dp)
                        .border(1.dp, Color(0xFF00C853), RoundedCornerShape(6.dp)),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = CenterVertically
                ) {
                    // Decrease (-) Button
                    IconButton(onClick = {
//                            viewModel.updateCartQuantity(product.productId, productQuantity - 1)
//                            onRemoveFromCart(product.productId)
                    }) {
                        Image(
                            painter = painterResource(Res.drawable.minus),
                            contentDescription = "minus",
                            modifier = Modifier.size(16.dp)
//                                tint = if (isInWishlist) Color.Red else Color.Gray
                        )

                    }

                    // Quantity Text
                    Text(
                        text = productQuantity.toString(),
                        style = ProductTypography().productQuantity,
                    )

                    // Increase (+) Button
                    IconButton(onClick = {
//                            viewModel.updateCartQuantity(product.productId, productQuantity + 1)
//                            onAddToCart(product.productId)
                    }) {

                        Image(
                            painter = painterResource(Res.drawable.plus),
                            contentDescription = "add",
                            modifier = Modifier.size(16.dp)

//                                tint = if (isInWishlist) Color.Red else Color.Gray
                        )
//                            Icon(
//                                imageVector = Icons.Default.Add,
//                                contentDescription = "Increase",
//                                tint = Color(0xFF00C853)
//                            )
                    }
                }
            } else {
                // Add to Cart Button
                Button(
                    onClick = {
//                            viewModel.updateCartQuantity(product.productId, productQuantity + 1)
//                            onAddToCart(product.productId)
                    },
                    shape = RoundedCornerShape(6.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(36.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF00C853), // Green
                        contentColor = Color.White
                    )
                ) {
                    Image(
                        painter = painterResource(Res.drawable.add_to_cart),
                        contentDescription = "Add to Cart",
                        modifier = Modifier.size(22.dp)
                    )
                    Spacer_4dp()
                    //Text(text = "Add to Cart", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}