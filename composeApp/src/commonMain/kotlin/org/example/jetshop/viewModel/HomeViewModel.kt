package org.example.jetshop.viewModel

import cafe.adriel.voyager.core.model.ScreenModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.example.jetshop.model.home.HomeResponse
import org.example.jetshop.remote.ApiResponse
import kotlinx.coroutines.launch
import org.example.jetshop.model.category.CategoryDetailsResponse
import org.example.jetshop.model.productDetails.ProductDetailsResponse
import org.example.jetshop.repo.home.HomeRepo


class HomeViewModel(private val repo: HomeRepo) : ScreenModel {

    // Fetch username from DataStore as Flow
//    val userIsPrimeActive: StateFlow<String?> = dataStoreHelper.getValue(UserDataStore.isUserPrimeActive)
//        .stateIn(viewModelScope, SharingStarted.Lazily, "")
    private val _homeState = MutableStateFlow<ApiResponse<HomeResponse>>(ApiResponse.Idle)
    val homeState = _homeState.asStateFlow()

    fun fetchHome() {
        // Use Voyager’s built-in coroutine scope
        CoroutineScope(Dispatchers.Default).launch {
            _homeState.value = ApiResponse.Loading
            try {
                val response = repo.home()
                _homeState.value = ApiResponse.Success(response)
            } catch (e: Exception) {
                _homeState.value = ApiResponse.Error(e.message ?: "Unknown error")
            }
        }
    }

    private val _productDetails=MutableStateFlow<ApiResponse<ProductDetailsResponse>>(ApiResponse.Idle)
    val productDetails=_productDetails.asStateFlow()

    fun fetchProductDetails( productId:String){
        CoroutineScope(Dispatchers.Default).launch {
            _productDetails.value=ApiResponse.Loading
            try {
                val response=repo.productDetails(productId)
                _productDetails.value=ApiResponse.Success(response)
            }catch (e:Exception){
                _productDetails.value=ApiResponse.Error(e.message?:"Unknown error")
            }

        }

    }

    private val _categoryDetails=MutableStateFlow<ApiResponse<CategoryDetailsResponse>>(ApiResponse.Idle)
    val categoryDetails=_categoryDetails.asStateFlow()


    fun fetchCategoryDetails(categoryId:String){
        CoroutineScope(Dispatchers.Default).launch {
            _categoryDetails.value=ApiResponse.Loading
            try {
                val response=repo.categoryDetails(categoryId)
                _categoryDetails.value=ApiResponse.Success(response)
            }catch (e:Exception){
                _categoryDetails.value=ApiResponse.Error(e.message?:"Unknown error")
            }

        }
    }
}

