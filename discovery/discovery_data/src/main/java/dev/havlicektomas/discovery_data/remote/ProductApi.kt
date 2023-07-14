package dev.havlicektomas.discovery_data.remote

import dev.havlicektomas.discovery_data.remote.dto.ProductDto
import dev.havlicektomas.discovery_data.remote.dto.ProductSearchDto
import dev.havlicektomas.discovery_domain.model.ProductCategory
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ProductApi {

    @GET("products")
    suspend fun searchFood(
        @Query("search_terms") query: String,
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int
    ): Response<ProductSearchDto>

    @GET("products/categories")
    suspend fun getProductCategories(): Response<List<ProductCategory>>

    @GET("products/favourites")
    suspend fun getFavouriteProducts(): Response<List<ProductDto>>

    @POST
    suspend fun toggleFavouriteProduct(
        @Body productDto: ProductDto
    ): Response<List<ProductDto>>

    companion object {
        const val BASE_URL = "https://api.example.com/"
    }
}