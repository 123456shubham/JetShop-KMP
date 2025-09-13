package org.example.jetshop.model.auth.register

import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponse(
    val flag: String? = null,
    val message: String? = null,
    val status: String? = null
)