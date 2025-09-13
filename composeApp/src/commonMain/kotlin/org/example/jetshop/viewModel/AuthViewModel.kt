package org.example.jetshop.viewModel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.jetshop.model.auth.register.RegisterRequest
import org.example.jetshop.model.auth.register.RegisterResponse
import org.example.jetshop.repo.auth.RegisterRepo
import org.example.jetshop.remote.ApiResponse
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.ScreenModel

class AuthViewModel(private val repo: RegisterRepo) : ScreenModel {

    private val _registerState = MutableStateFlow<ApiResponse<RegisterResponse>>(ApiResponse.Idle)
    val registerState = _registerState.asStateFlow()

    fun register(request: RegisterRequest) {
        _registerState.value = ApiResponse.Loading
        CoroutineScope(Dispatchers.Default).launch {
            try {
                val response = repo.register(request)
                _registerState.value = ApiResponse.Success(response)
            } catch (e: Exception) {
                _registerState.value = ApiResponse.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun resetRegisterState() {
        _registerState.value = ApiResponse.Idle
    }
}

