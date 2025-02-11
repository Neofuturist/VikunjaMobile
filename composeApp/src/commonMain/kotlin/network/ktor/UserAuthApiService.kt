package network.ktor

import data.model.Resource
import data.model.auth.AuthData
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import network.pathUrl

class UserAuthApiService(
    private val ktor: KtorApi
) {
    suspend fun auth(authData: AuthData): Resource<HttpResponse> {
        return try {
            Resource.Success(ktor.client.post {
                pathUrl("login")
                setBody(authData)
                contentType(ContentType.Application.Json)
            })
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }
}