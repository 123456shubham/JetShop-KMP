package org.example.jetshop.remote

import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import io.ktor.client.*

const val BASE_URL = "https://pixeldev.in/webservices/jetshop/"

val httpClient = HttpClient {

    defaultRequest {
        url(BASE_URL)
        contentType(ContentType.Application.Json)
    }
    install(ContentNegotiation) {
        json(
            Json {
                prettyPrint = true
                isLenient=true
                ignoreUnknownKeys = true
                explicitNulls=false
            }
        )
    }

    install(HttpTimeout){
        requestTimeoutMillis=60_000
    }



    install(Logging) {
        logger = Logger.DEFAULT
        level = LogLevel.ALL
        logger=object : Logger{
            override fun log(message: String) {
                println(message)
            }

        }
        sanitizeHeader { header ->
            header == HttpHeaders.Authorization
        }
    }
}
