package org.example.jetshop.model.home

import kotlinx.serialization.Serializable

@Serializable

data class Section(
    val products: List<Product?>?,
    val section_id: String?,
    val section_name: String?
)