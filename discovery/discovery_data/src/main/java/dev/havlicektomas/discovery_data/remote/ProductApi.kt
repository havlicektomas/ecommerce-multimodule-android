package dev.havlicektomas.discovery_data.remote

import dev.havlicektomas.discovery_data.remote.dto.ProductCategoryDto
import dev.havlicektomas.discovery_data.remote.dto.ProductDto
import dev.havlicektomas.discovery_data.remote.dto.ProductSearchDto
import dev.havlicektomas.discovery_domain.model.ProductCategory
import kotlinx.coroutines.delay
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import kotlin.time.Duration.Companion.seconds

interface ProductApi {
    @GET("products")
    suspend fun searchProducts(
        @Query("search_terms") query: String,
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int
    ): Response<ProductSearchDto>

    @POST
    suspend fun toggleFavouriteProduct(
        @Body productDto: ProductDto
    ): Response<List<ProductDto>>

    companion object {
        const val BASE_URL = "https://api.example.com/"
    }
}

suspend fun fakeProductsApiResponse(category: String): Response<ProductSearchDto> {
    delay(1.seconds)
    return when (category) {
        "home" -> {
            fakeHomeProductsApiResponse()
        }
        "favourite" -> {
            fakeFavouriteProductsApiResponse()
        }
        "category" -> {
            fakeSearchProductsApiResponse()
        }
        else -> {
            fakeSearchProductsApiResponse()
        }
    }
}

private fun fakeHomeProductsApiResponse() = Response.success(
    ProductSearchDto(
        page = 1,
        pageSize = 9,
        products = listOf(
            fakeFeaturedProductDto(), fakeFeaturedProductDto(), fakeFeaturedProductDto(),
            fakeOnsaleProductDto(), fakeOnsaleProductDto(), fakeOnsaleProductDto(),
            fakeForyouProductDto(), fakeForyouProductDto(), fakeForyouProductDto(),
        )
    )
)

private fun fakeFavouriteProductsApiResponse() = Response.success(
    ProductSearchDto(
        page = 1,
        pageSize = 9,
        products = listOf(
            fakeFeaturedProductDto(), fakeFeaturedProductDto(), fakeFeaturedProductDto()
        )
    )
)

private fun fakeSearchProductsApiResponse() = Response.success(
    ProductSearchDto(
        page = 1,
        pageSize = 9,
        products = listOf(
            fakeFeaturedProductDto(), fakeFeaturedProductDto(), fakeFeaturedProductDto(),
            fakeOnsaleProductDto(), fakeOnsaleProductDto(), fakeOnsaleProductDto(),
            fakeForyouProductDto(), fakeForyouProductDto(), fakeForyouProductDto(),
        )
    )
)

private fun fakeFeaturedProductDto() = ProductDto(
    id = "123",
    name = "Product 1",
    brand = "SomeBrand",
    description = "Some description goes here",
    price = 9.99,
    category = "Category1",
    tag = "featured",
    imageUrl = "https://firebasestorage.googleapis.com/v0/b/ecommerce-multimodule.appspot.com/o/cabbage.webp?alt=media&token=de5c45db-a017-43b1-aae8-9dd0bf9f1085"
)

private fun fakeOnsaleProductDto() = ProductDto(
    id = "123",
    name = "Product 1",
    brand = "SomeBrand",
    description = "Some description goes here",
    price = 9.99,
    category = "Category1",
    tag = "onsale",
    imageUrl = "https://firebasestorage.googleapis.com/v0/b/ecommerce-multimodule.appspot.com/o/cabbage.webp?alt=media&token=de5c45db-a017-43b1-aae8-9dd0bf9f1085"
)

private fun fakeForyouProductDto() = ProductDto(
    id = "123",
    name = "Product 1",
    brand = "SomeBrand",
    description = "Some description goes here",
    price = 9.99,
    category = "Category1",
    tag = "foryou",
    imageUrl = "https://firebasestorage.googleapis.com/v0/b/ecommerce-multimodule.appspot.com/o/cabbage.webp?alt=media&token=de5c45db-a017-43b1-aae8-9dd0bf9f1085"
)