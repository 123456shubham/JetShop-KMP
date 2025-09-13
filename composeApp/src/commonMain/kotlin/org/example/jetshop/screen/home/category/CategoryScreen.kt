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
import org.example.jetshop.components.Spacer_8dp
import org.example.jetshop.model.home.Category
import org.example.jetshop.screen.home.banner.ItemType
import org.example.jetshop.screen.home.brand.CommonCircleItem
import org.example.jetshop.ui.theme.appMainTypography


@Composable
fun CategoriesListing(
    categories: List<Category>?,

    ) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            "Category",
            style = appMainTypography().subHeader,
        )
        Text("see all",
            style = appMainTypography().seeAllText,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.clickable {
//                navigateToCategories()
            })
    }
    Spacer_8dp()
    LazyRow(
        modifier = Modifier.fillMaxWidth(), contentPadding = PaddingValues(8.dp)
    ) {

        items(count = categories?.size ?: 0,
            key = { index -> categories!![index].id!! }, // Unique key for each item
            contentType = { index -> categories!![index].name } // Optional content type
        ) { index ->
            CommonCircleItem(item = ItemType.Category(categories!![index])) {
//                navController.navigate(Screen.CategoryBrandDetails.route + "/category/${categories[index].id}")
            }
        }
    }
}
