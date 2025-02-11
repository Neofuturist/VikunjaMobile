package network

import data.model.Resource
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.statement.HttpResponse
import io.ktor.http.appendPathSegments
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HttpUtil(

) {
    suspend inline fun <reified T> checkResponse(response: Resource<HttpResponse>): Resource<T> {
        return when(response) {
            is Resource.Loading -> {
                Resource.Loading
            }
            is Resource.Error -> {
                Resource.Error(response.error)
            }
            is Resource.Success -> {
                when (response.data.status.value) {
                    in 100..399 -> {
                        try {
                            setAuthState(AuthState.AUTHORIZED)
                            Resource.Success(response.data.body())
                        }catch (e: Exception){
                            Resource.Error(e)
                        }catch (e: Throwable){
                            Resource.Error(e)
                        }
                    }
                    401 -> {
                        if (response.data.status.value == 401 && authState.value != AuthState.UNAUTHORIZED){
                            delay(500)
                            setAuthState(AuthState.UNAUTHORIZED)
                        }
                        Resource.Error(Throwable(message = "Unauthorized"))
                    }
                    else -> {
                        Resource.Error(Throwable(message = "X3"))
                    }
                }
            }
        }
    }

    private val _authState = MutableStateFlow(AuthState.INIT)
    val authState: StateFlow<AuthState> = _authState

    fun setAuthState(value: AuthState) {
        _authState.value = value
    }

    enum class AuthState {
        INIT,
        AUTHORIZED,
        UNAUTHORIZED,
        ERROR
    }
}

fun HttpRequestBuilder.pathUrl(path: String) {
    url {
        appendPathSegments(path)
    }
}