package network.ktor

import data.model.Resource
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import network.pathUrl

class ProjectApiService(
    private val ktor: KtorApi
) {
    suspend fun getProjects(): Resource<HttpResponse> {
        return try {
            Resource.Success(ktor.client.get {
                pathUrl("projects")
                contentType(ContentType.Application.Json)
            })
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }
}