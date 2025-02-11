package data.model.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthData(
    @SerialName("long_token")
    val longToken: Boolean = true,
    val password: String? = null,
    val username: String? = null
)