package org.example.jetshop.repo.auth

import io.ktor.client.HttpClient
import io.ktor.client.call.body

import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import kotlinx.serialization.json.Json
import org.example.jetshop.model.MyError
import org.example.jetshop.model.auth.register.RegisterRequest
import org.example.jetshop.model.auth.register.RegisterResponse
import org.example.jetshop.remote.ApiResponse
import org.example.jetshop.remote.BASE_URL
import org.example.jetshop.remote.createHttpClient
import org.example.jetshop.remote.httpClient

class RegisterRepo {


    private val client: HttpClient = createHttpClient()

    suspend fun register(request: RegisterRequest): RegisterResponse {
        return client.post(BASE_URL +"register.php") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }
//    suspend fun register(request: RegisterRequest): ApiResponse<RegisterResponse> {
//        return try {
//            val response: RegisterResponse = networkModule.post("register.php") {
//              contentType(ContentType.Application.Json)
//                setBody(request)
//            }.body()
//
//            println("✅ Response received: $response") // <-- print parsed response
//            ApiResponse.Success(response)
//
//        } catch (e: Exception) {
//            println("❌ Exception during register: ${e.message}")
//            e.printStackTrace()
//            ApiResponse.Error(e.message ?: "Unknown error")
//        }
//    }
//    suspend fun register(registerRequest: RegisterRequest): HttpResponse {
//
//        return httpClient.post {
//
//            url("https://pixeldev.in/webservices/jetshop/"+"register.php")
//            contentType(ContentType.Application.Json)
//            setBody(registerRequest)
//            println("url : ${httpClient.post("register.php")}")
//
//        }
//
//    }
//    suspend fun register(request: RegisterRequest): ApiResponse<RegisterResponse> {
//        return try {
//            val response: RegisterResponse = httpClient.post("register.php") {
//                contentType(ContentType.Application.Json)
//                setBody(request)
//            }.body()
//            println("✅ API RESPONSE: $response")
//            ApiResponse.Success(response)
//        } catch (e: Exception) {
//            println("❌ API ERROR: ${e.message}")
//            ApiResponse.Error(e.message ?: "Unknown error")
//        }
//    }
//    suspend fun register(registerRequest: RegisterRequest): ApiResponse<RegisterResponse> {
//        return try {
//            val response: HttpResponse = httpClient.post("register.php") {
////                contentType(ContentType.Application.Json)
//                setBody(registerRequest)
////                println("url : ${httpClient.post("register.php")}")
//            }
//
//            if (response.status.isSuccess()) {
//                val data: RegisterResponse = response.body()
//                println("data : $data")
//                ApiResponse.Success(data)
//            } else {
//                // Parse error body
//                val errorText = response.bodyAsText()
//                println("errorText : $errorText")
//                val error = try {
//                    Json.decodeFromString(MyError.serializer(), errorText)
//                } catch (e: Exception) {
//                    println("RegisterException : ${e.message}")
//
//                    MyError(e.message.toString(), errorText.ifBlank { "Something went wrong" })
//                }
//                ApiResponse.Error(error.message)
//            }
//        } catch (e: Exception) {
//            println("NewRegisterException : ${e.message}")
//
//            ApiResponse.Error(e.message ?: "Unknown error")
//        }
//    }





}