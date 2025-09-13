package org.example.jetshop.model.auth.register

import kotlinx.serialization.Serializable
@Serializable
data class RegisterRequest(
    val email: String?,
    val first_name: String?,
    val last_name: String?,
    val password: String?,
    val phone: String?,
    val referred_by: String?
)