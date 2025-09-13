package org.example.jetshop.remote

//sealed class ApiResponse<out T> {
//    class Loading<T> : ApiResponse<T>()
//    data class Success<T>(val data: T) : ApiResponse<T>()
//    data class Error<T>(val message: String) : ApiResponse<T>()
//    object Idle : ApiResponse<Nothing>()
//
//}
//

sealed class ApiResponse<out T> {
    object Idle : ApiResponse<Nothing>()
    object Loading : ApiResponse<Nothing>()
    data class Success<T>(val data: T) : ApiResponse<T>()
    data class Error(val message: String) : ApiResponse<Nothing>()
}