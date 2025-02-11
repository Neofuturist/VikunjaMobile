package data.model.auth

import kotlinx.serialization.Serializable

@Serializable
data class TokenData(
    val token: String? = null
)