package org.example.jetshop.model.home

import kotlinx.serialization.Serializable

@Serializable
data class SliderImage(
    val description: String?,
    val image_url: String?,
    val redirect_url: String?,
    val slider_id: String?,
    val title: String?
)