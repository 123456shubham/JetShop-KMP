package org.example.jetshop.model.productDetails

import kotlinx.serialization.Serializable

@Serializable
data class Review(
    val user_name: String?,
    val reviewRating: String,
    val reviewTitle: String,
    val reviewComment: String
)