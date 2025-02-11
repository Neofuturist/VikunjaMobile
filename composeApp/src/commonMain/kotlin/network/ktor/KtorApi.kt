package network.ktor

import data.Constants.Companion.API_URL
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.accept
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.component.KoinComponent
import org.lighthousegames.logging.logging

class KtorApi(): KoinComponent {
    private val json = Json {
        ignoreUnknownKeys = true
        useAlternativeNames = false
        prettyPrint = true
    }

    lateinit var client: HttpClient

    private fun init() {
        client = HttpClient {
            defaultRequest {
                url(API_URL)
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
            }
            install(ContentNegotiation) {
                json(json)
            }
            install(Logging) {
                level = LogLevel.ALL
                logger = object : Logger {
                    override fun log(message: String) {
                        logging("Ktor Client").d { message }
                    }
                }
            }
        }
    }

    init {
        init()
    }
}