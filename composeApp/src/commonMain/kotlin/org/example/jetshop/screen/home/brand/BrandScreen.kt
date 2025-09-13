package org.example.jetshop.screen.home.brand

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import org.example.jetshop.components.Spacer_8dp
import org.example.jetshop.model.home.Brand
import org.example.jetshop.screen.home.banner.ItemType
import org.example.jetshop.ui.theme.ProductTypography
import org.example.jetshop.ui.theme.appMainTypography

@Composable
fun BrandListing(
    brandData: List<Brand>,

) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            "Brands",
            style = appMainTypography().subHeader,
        )
        Text("see all",
            style = appMainTypography().seeAllText,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.clickable {
//                navigateToBrand()
            })
    }
    Spacer_8dp()
    LazyRow(
        modifier = Modifier.fillMaxWidth(), contentPadding = PaddingValues(8.dp)
    ) {

        items(count = brandData.size ?: 0,
            key = { index -> brandData[index].brand_id!! }, // Unique key for each item
            contentType = { index -> brandData[index].brand_name } // Optional content type
        ) { index ->
            CommonCircleItem(item = ItemType.Brand(brandData[index])) {
//                navController.navigate(Screen.CategoryBrandDetails.route + "/brand/${brandData[index].brandId}")
            }
        }
    }
}
@Composable
fun CommonCircleItem(
    item: ItemType,
    onClick: () -> Unit
) {

    val imageUrl: String
    val label: String

    when (item) {
        is ItemType.Brand -> {
            imageUrl = item.brandData.brand_logo.toString()
            label = item.brandData.brand_name.toString()
        }
        is ItemType.Category -> {
            imageUrl = item.categoryData.icon.toString()
            label = item.categoryData.name.toString()
        }
        else -> {
            imageUrl = ""
            label = ""
        }
    }

    Column(
        modifier = Modifier
            .padding(0.dp)
            .width(72.dp)
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .border(
                    width = 1.dp,
                    color = Color(0xFFE0E0E0),
                    shape = CircleShape
                )
                .background(Color(0xFFF6F6F6)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = rememberAsyncImagePainter(imageUrl),
                contentDescription = null,
                modifier = Modifier.size(32.dp),
                contentScale = ContentScale.Fit
            )
        }

        Spacer_8dp()

        Text(
            text = label,
            style = ProductTypography().labelSmall,
            fontWeight = FontWeight.Medium,
            maxLines = 1,
            textAlign = TextAlign.Center
        )
    }
}
