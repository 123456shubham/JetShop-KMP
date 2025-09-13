package org.example.jetshop.model

import kotlinx.serialization.Serializable
@Serializable
data class MyError(
    val status: String,
    val message: String
)