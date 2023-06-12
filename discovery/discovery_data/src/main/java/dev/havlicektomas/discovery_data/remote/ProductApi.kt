package dev.havlicektomas.discovery_data.remote

import dev.havlicektomas.discovery_data.remote.dto.ProductSearchDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductApi {

    @GET("products")
    suspend fun searchFood(
        @Query("search_terms") query: String,
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int
    ): Response<ProductSearchDto>

    companion object {
        const val BASE_URL = "https://api.example.com/"
    }
}