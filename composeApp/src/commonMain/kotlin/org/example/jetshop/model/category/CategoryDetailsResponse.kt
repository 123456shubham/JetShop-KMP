package org.example.jetshop.model.category

import kotlinx.serialization.Serializable


@Serializable
data class CategoryDetailsResponse(
    var products: List<Product?>?,
    var success: Boolean?
)