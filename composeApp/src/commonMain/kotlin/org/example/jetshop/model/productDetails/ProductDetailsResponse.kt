package org.example.jetshop.model.productDetails

import kotlinx.serialization.Serializable
@Serializable
data class ProductDetailsResponse(
    var message: String?,
    var product: Product?,
    var reviewsData: ReviewsData?,
    var status: String?
)