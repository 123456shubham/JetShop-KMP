package org.example.jetshop.screen.home.product

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil3.compose.AsyncImage
import jetshop.composeapp.generated.resources.Res
import jetshop.composeapp.generated.resources.add_to_cart
import jetshop.composeapp.generated.resources.cart
import jetshop.composeapp.generated.resources.delete
import jetshop.composeapp.generated.resources.heart
import jetshop.composeapp.generated.resources.love
import jetshop.composeapp.generated.resources.minus
import jetshop.composeapp.generated.resources.plus
import org.example.jetshop.components.LoginPromptDialog
import org.example.jetshop.components.Spacer_12dp
import org.example.jetshop.components.Spacer_4dp
import org.example.jetshop.components.Spacer_8dp
import org.example.jetshop.model.home.Product
import org.example.jetshop.repo.home.HomeRepo
import org.example.jetshop.ui.theme.ProductTypography
import org.example.jetshop.viewModel.HomeViewModel
import org.jetbrains.compose.resources.painterResource


    @Composable
    fun ProductListing(
        productData: List<Product>,
        viewModel: HomeViewModel,
//        wishlistViewModel: WishlistViewModel,
        isLoggedIn: MutableState<Boolean>,
    ) {
        val navigator= LocalNavigator.current
        val showLoginDialog = remember { mutableStateOf(false) }
//        val userId by viewModel.userId.collectAsState()

//        Log.d("TAG_userId", "ProductListing: " + userId)
// Show the login prompt when needed
        LoginPromptDialog(showDialog = showLoginDialog.value,
            onDismiss = { showLoginDialog.value = false },
            onLoginClick = {
                showLoginDialog.value = false
//                navController.navigate(Screen.Login.route)
            })
//        LazyRow(
//            horizontalArrangement = Arrangement.spacedBy(0.dp),
//            contentPadding = PaddingValues(horizontal = 0.dp)
//        ) {
//            productData.size.let { productIndex ->
//                items(productIndex) { product ->
//                    var isInWishlist by remember { mutableStateOf(false) }
//
//                    LaunchedEffect(product) {
//        //                        wishlistViewModel.isProductInWishlist(productData[product].productId) {
//        //                            isInWishlist = it
//                    }
//                }
//        //                    val wishlistProduct = productData[product].toWishlistProduct()
//
//                ProductCard(
//                    product = productData[productIndex],
//                    viewModel = viewModel,
//        //                        onAddToCart = { productId ->
//        //                            if (isLoggedIn.value) {
//        //                                viewModel.addToCart(productId, "1")
//        //                            } else {
//        //                                showLoginDialog.value = true
//        //                            }
//        //                        },
//        //                        onViewProduct = { selectedProduct ->
//        //                            navController.navigate("${Screen.ProductDetails.route}/${selectedProduct.productId}")
//        //                        },
//        //                        onWishlistToggle = {
//        //                            if (isInWishlist) {
//        //                                wishlistViewModel.removeFromWishlist(wishlistProduct)
//        //                            } else {
//        //                                wishlistViewModel.addToWishlist(wishlistProduct)
//        //                            }
//        //                            isInWishlist = !isInWishlist // Toggle UI state
//        //                        },
//        //                        isInWishlist = isInWishlist,
//        //                        onRemoveFromCart = { produdtId ->
//        //                            viewModel.removeFromCart(produdtId)
//        //                        }
//                )
//            }
//
//
//            }

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(0.dp),
            contentPadding = PaddingValues(horizontal = 0.dp)
        ) {
            items(productData.size) { product ->
                var isInWishlist by remember { mutableStateOf(false) }

                LaunchedEffect(product) {
                    // check wishlist if needed
                }

                ProductCard(
                    product = productData[product],
                    viewModel = viewModel,
                )
            }
        }

    }



    @Composable
    fun ProductCard(
        product: Product,
        viewModel: HomeViewModel,
//        onAddToCart: (String) -> Unit,
//        onRemoveFromCart: (String) -> Unit,
////        onViewProduct: (ProductData) -> Unit,
//        onWishlistToggle: () -> Unit,
//        isInWishlist: Boolean,
    ) {
        // Quantity state initialized with API value
//        val quantity by viewModel.cartQuantities.collectAsState()
//        val productQuantity = quantity[product.product_id] ?: product.user_cart_quantity?.toInt() ?: 0

        val productQuantity=1

        val navigator=LocalNavigator.currentOrThrow
        Card(elevation = CardDefaults.cardElevation(6.dp),
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = BorderStroke(1.dp, Color(0xFFE2E2E2)),
            modifier = Modifier
                .width(180.dp)
                .padding(8.dp)
                .clickable { navigator?.push(ProductDetailsScreen(product.product_id.toString())) }
        )
        {
            Column(
                modifier = Modifier.padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .clip(RoundedCornerShape(8.dp))
                ) {
                    AsyncImage(
                        model = product.product_image_url,
                        contentDescription = product.product_name,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.fillMaxSize()
                    )
                    IconButton(
//                        onClick = onWishlistToggle,
                        onClick = {},
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .background(Color.White, CircleShape)
                            .size(32.dp)
                    ) {
                        Image(
//                            painter=painterResource( if (isInWishlist) Res.drawable.love else Res.drawable.heart),
                            painter=painterResource(  Res.drawable.love),
                            contentDescription = "Wishlist",
                            modifier = Modifier.size(18.dp)
//                            tint = if (isInWishlist) Color.Red else Color.Gray
                        )
                    }
                }

                Spacer_8dp()

                // Product Name
                Text(
                    text = product.product_name.toString(),
                    style = ProductTypography().prodTitleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                Spacer_12dp()

                // Price and Discount
                Row(
                    verticalAlignment = CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "₹${product.product_discount_price}",
//                        color = MaterialTheme.colorScheme.primary,
                        style = ProductTypography().prodPriceBold,
                        modifier = Modifier.padding(end = 4.dp)
                    )
                    Text(
                        text = "₹${product.product_price}",
                        style = ProductTypography().prodDiscountPrice,
                        color = Color.Gray)
                }

                Spacer_8dp()

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
