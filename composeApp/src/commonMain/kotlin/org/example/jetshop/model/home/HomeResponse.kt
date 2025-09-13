package org.example.jetshop.model.home

import kotlinx.serialization.Serializable

@Serializable
data class HomeResponse(
    val brands: List<Brand?>?,
    val categories: List<Category?>?,
    val sections: List<Section?>?,
    val slider_images: List<SliderImage?>?,
    val status: String?
)