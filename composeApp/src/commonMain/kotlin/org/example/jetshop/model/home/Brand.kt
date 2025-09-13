package org.example.jetshop.model.home

import kotlinx.serialization.Serializable
@Serializable
data class Brand(
    val brand_description: String?,
    val brand_id: String?,
    val brand_logo: String?,
    val brand_name: String?,
    val created_at: String?,
    val status: String?,
    val updated_at: String?,
    val website_url: String?
)