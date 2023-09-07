package dev.havlicektomas.discovery_data.remote

import dev.havlicektomas.discovery_data.remote.dto.ProductCategoryDto
import dev.havlicektomas.discovery_data.remote.dto.ProductDto
import dev.havlicektomas.discovery_domain.model.ProductCategory
import kotlinx.coroutines.delay
import retrofit2.Response
import retrofit2.http.GET
import kotlin.time.Duration.Companion.seconds

interface ProductCategoryApi {
    @GET("products/categories")
    suspend fun getProductCategories(): Response<List<ProductCategoryDto>>

    companion object {
        const val BASE_URL = "https://api.example.com/"
    }
}

suspend fun fakeProductCategoriesApiResponse():Response<List<ProductCategoryDto>> {
    delay(1.seconds)
    return Response.success(
        listOf(
            ProductCategoryDto("Fruits", "", "fruits"),
            ProductCategoryDto("Vegetables", "", "vegetables")
        )
    )
}
