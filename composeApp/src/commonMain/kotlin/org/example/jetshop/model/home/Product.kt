package org.example.jetshop.model.home

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val product_brand_id: Int?,
    val product_category_id: Int?,
    val product_color: String?,
    val product_created_at: String?,
    val product_description: String?,
    val product_dimensions: String?,
    val product_discount_price: String?,
    val product_id: String?,
    val product_image_url: String?,
    val product_is_active: Int?,
    val product_is_featured: Int?,
    val product_name: String?,
    val product_price: String?,
    val product_rating: String?,
    val product_size: String?,
    val product_stock_quantity: Int?,
    val product_thumbnail_url: String?,
    val product_total_reviews: Int?,
    val product_updated_at: String?,
    val product_weight: String?,
    val user_cart_quantity: Int?
)