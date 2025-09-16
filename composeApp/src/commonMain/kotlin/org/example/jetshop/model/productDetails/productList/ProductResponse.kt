package org.example.jetshop.model.productDetails.productList

import kotlinx.serialization.Serializable

@Serializable
data class ProductResponse(
    var current_page: Int?,
    var message: String?,
    var products: List<Product?>?,
    var success: Boolean?,
    var total_pages: Int?,
    var total_products: String?
)