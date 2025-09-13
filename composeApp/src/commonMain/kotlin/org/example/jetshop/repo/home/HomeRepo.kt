package org.example.jetshop.repo.home

import androidx.compose.ui.graphics.vector.addPathNodes
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.Parameters
import io.ktor.http.contentType
import org.example.jetshop.model.home.HomeResponse
import org.example.jetshop.model.productDetails.ProductDetailsResponse
import org.example.jetshop.remote.BASE_URL
import org.example.jetshop.remote.createHttpClient

class HomeRepo {
    private val client: HttpClient = createHttpClient()
    suspend fun home(): HomeResponse {
        return client.get(BASE_URL+"get_slider_category_product.php").body()
    }

    suspend fun productDetails(productId: String): ProductDetailsResponse {
        return client.post(BASE_URL + "get_productDetails.php") {
            contentType(ContentType.Application.FormUrlEncoded)
            setBody(FormDataContent(Parameters.build {
                append("product_id", productId)
            }))
        }.body()
    }
}