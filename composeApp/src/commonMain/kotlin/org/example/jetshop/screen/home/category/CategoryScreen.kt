package org.example.jetshop.screen.home.category

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.example.jetshop.components.Spacer_8dp
import org.example.jetshop.model.home.Category
import org.example.jetshop.screen.home.banner.ItemType
import org.example.jetshop.screen.home.brand.CommonCircleItem
import org.example.jetshop.ui.theme.appMainTypography



//data class CategoryScreen(var categories: List<Category>?): Screen{
//    @Composable
//    override fun Content() {
//        val navigator= LocalNavigator.current
//
//        CategoriesListing(categories,navigator)
//    }
//}
//@Composable
//fun CategoriesListing(
//    categories: List<Category>?,
//    nav: Navigator?   // ✅ add parameter
//
//
//) {
//
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(horizontal = 16.dp),
//        verticalAlignment = CenterVertically,
//        horizontalArrangement = Arrangement.SpaceBetween
//    ) {
//        Text(
//            "Category",
//            style = appMainTypography().subHeader,
//        )
//        Text("see all",
//            style = appMainTypography().seeAllText,
//            color = MaterialTheme.colorScheme.primary,
//            modifier = Modifier.clickable {
////                navigateToCategories()
//            })
//    }
//    Spacer_8dp()
//    LazyRow(
//        modifier = Modifier.fillMaxWidth(), contentPadding = PaddingValues(8.dp)
//    ) {
//
//        items(count = categories?.size ?: 0,
//            key = { index -> categories!![index].id!! }, // Unique key for each item
//            contentType = { index -> categories!![index].name } // Optional content type
//        ) { index ->
//            CommonCircleItem(item = ItemType.Category(categories!![index])) {
////                navController.navigate(Screen.CategoryBrandDetails.route + "/category/${categories[index].id}")
//
//                nav?.push(CategoryBrandScreen(true))
////                navigator?.push(CategoryListScreen(categories[index].name.toString()))
//            }
//        }
//    }
//}



@Composable
fun CategoriesListing(categories: List<Category>?) {
    val navigator = LocalNavigator.currentOrThrow

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text("Category", style = appMainTypography().subHeader)
        Text(
            "see all",
            style = appMainTypography().seeAllText,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.clickable {
                // TODO: Implement "See all" navigation
            }
        )
    }

    Spacer_8dp()

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(
            count = categories?.size ?: 0,
            key = { index -> categories!![index].id!! },
            contentType = { index -> categories!![index].name }
        ) { index ->
            CommonCircleItem(item = ItemType.Category(categories!![index])) {
//                navigator.push(CategoryBrandScreen(true))
                navigator.push(CategoryListVoyagerScreen(categories[index].name.toString(),categories[index].id.toString()))
            }
        }
    }
}
