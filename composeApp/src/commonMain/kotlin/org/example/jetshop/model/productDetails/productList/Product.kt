package org.example.jetshop.model.productDetails.productList

import kotlinx.serialization.Serializable

@Serializable

data class Product(
    var product_brand_id: String?,
    var product_category_id: String?,
    var product_color: String?,
    var product_created_at: String?,
    var product_description: String?,
    var product_dimensions: String?,
    var product_discount_price: String?,
    var product_id: String?,
    var product_image_url: String?,
    var product_is_active: String?,
    var product_is_featured: String?,
    var product_name: String?,
    var product_price: String?,
    var product_rating: String?,
    var product_size: String?,
    var product_stock_quantity: String?,
    var product_thumbnail_url: String?,
    var product_total_reviews: String?,
    var product_updated_at: String?,
    var product_weight: String?
)