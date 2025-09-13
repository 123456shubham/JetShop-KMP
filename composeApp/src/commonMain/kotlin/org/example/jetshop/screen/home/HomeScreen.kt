package org.example.jetshop.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import org.example.jetshop.components.CenteredCircularProgressIndicator
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
import org.example.jetshop.ui.theme.appMainTypography
import org.example.jetshop.ui.theme.white
import org.example.jetshop.viewModel.AuthViewModel
import org.example.jetshop.viewModel.HomeViewModel

object HomeScreen : Screen {
    @Composable
    override fun Content() {

        val navigator= LocalNavigator.current
        val viewModel: HomeViewModel = rememberScreenModel { HomeViewModel(HomeRepo()) }


        Box(modifier = Modifier.fillMaxSize().statusBarsPadding().background(white)){

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