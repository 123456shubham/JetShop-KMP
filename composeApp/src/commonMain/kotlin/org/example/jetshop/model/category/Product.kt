package org.example.jetshop.model.category
import kotlinx.serialization.Serializable

@Serializable
data class Product(
    var product_brand_id: Int?,
    var product_category_id: Int?,
    var product_color: String?,
    var product_created_at: String?,
    var product_description: String?,
    var product_dimensions: String?,
    var product_discount_price: String?,
    var product_id: String?,
    var product_image_url: String?,
    var product_is_active: Int?,
    var product_is_featured: Int?,
    var product_name: String?,
    var product_price: String?,
    var product_rating: String?,
    var product_size: String?,
    var product_stock_quantity: Int?,
    var product_thumbnail_url: String?,
    var product_total_reviews: Int?,
    var product_updated_at: String?,
    var product_weight: String?
)