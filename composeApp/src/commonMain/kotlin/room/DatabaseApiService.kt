package room

import data.Constants.Companion.DB_TEST_URL
import data.model.Resource
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import network.ktor.KtorApi

class DatabaseApiService(
    private val ktor: KtorApi
) {
    suspend fun getItems(): Resource<HttpResponse> {
        return try {
            Resource.Success(ktor.client.get {
                url {
                    host = DB_TEST_URL
                }
                contentType(ContentType.Application.Json)
            })
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }
}