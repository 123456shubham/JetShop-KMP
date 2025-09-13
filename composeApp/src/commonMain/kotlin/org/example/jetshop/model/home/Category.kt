package org.example.jetshop.model.home

import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val brief: String?,
    val created_at: String?,
    val draft: String?,
    val icon: String?,
    val id: String?,
    val name: String?,
    val priority: String?
)