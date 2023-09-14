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
        products = fakeFeaturedProductDtos() + fakeOnsaleProductDtos() + fakeForyouProductDtos()
    )
)

private fun fakeFavouriteProductsApiResponse() = Response.success(
    ProductSearchDto(
        page = 1,
        pageSize = 9,
        products = fakeFeaturedProductDtos()
    )
)

private fun fakeSearchProductsApiResponse() = Response.success(
    ProductSearchDto(
        page = 1,
        pageSize = 9,
        products = fakeFeaturedProductDtos() + fakeOnsaleProductDtos() + fakeForyouProductDtos()
    )
)

private fun fakeFeaturedProductDtos() = listOf(
    ProductDto(
        id = "123",
        name = "Apples",
        brand = "SomeBrand",
        description = "Some description goes here",
        price = 9.99,
        category = "fruits",
        tag = "featured",
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/ecommerce-multimodule.appspot.com/o/demo%2Ffruits%2Fapple.webp?alt=media&token=3030a598-e083-40c1-b372-ab06d151d67c"
    ),
    ProductDto(
        id = "123",
        name = "Bananas",
        brand = "SomeBrand",
        description = "Some description goes here",
        price = 9.99,
        category = "fruits",
        tag = "featured",
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/ecommerce-multimodule.appspot.com/o/demo%2Ffruits%2Fbanana.webp?alt=media&token=5ffa4dc6-7beb-42e6-a69d-362edc55c37a"
    ),
    ProductDto(
        id = "123",
        name = "Blueberries",
        brand = "SomeBrand",
        description = "Some description goes here",
        price = 9.99,
        category = "fruits",
        tag = "featured",
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/ecommerce-multimodule.appspot.com/o/demo%2Ffruits%2Fblueberry.webp?alt=media&token=e8fa83ee-c731-42fb-8c96-2684db784b1b"
    )
)

private fun fakeOnsaleProductDtos() = listOf(
    ProductDto(
        id = "123",
        name = "Raddish",
        brand = "SomeBrand",
        description = "Some description goes here",
        price = 9.99,
        category = "fruits",
        tag = "onsale",
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/ecommerce-multimodule.appspot.com/o/demo%2Fveggies%2Fradishes.webp?alt=media&token=c1c11568-a449-4cbc-b4f5-d3d4ff3a311b"
    ),
    ProductDto(
        id = "123",
        name = "Cabbage",
        brand = "SomeBrand",
        description = "Some description goes here",
        price = 9.99,
        category = "fruits",
        tag = "onsale",
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/ecommerce-multimodule.appspot.com/o/demo%2Fveggies%2Fcabbage.webp?alt=media&token=ba30f0c6-15bb-43ce-8d8e-df4eb2edcb90"
    ),
    ProductDto(
        id = "123",
        name = "Carrot",
        brand = "SomeBrand",
        description = "Some description goes here",
        price = 9.99,
        category = "fruits",
        tag = "onsale",
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/ecommerce-multimodule.appspot.com/o/demo%2Fveggies%2Fcarrots.webp?alt=media&token=b3eec187-0277-4a91-9ed3-88ef16e4c718"
    )
)

private fun fakeForyouProductDtos() = listOf(
    ProductDto(
        id = "123",
        name = "Pinapple",
        brand = "SomeBrand",
        description = "Some description goes here",
        price = 9.99,
        category = "fruits",
        tag = "foryou",
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/ecommerce-multimodule.appspot.com/o/demo%2Ffruits%2Fpinapple.webp?alt=media&token=13b4888e-d8ac-405e-b328-35aec118ec90"
    ),
    ProductDto(
        id = "123",
        name = "Strawberry",
        brand = "SomeBrand",
        description = "Some description goes here",
        price = 9.99,
        category = "fruits",
        tag = "foryou",
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/ecommerce-multimodule.appspot.com/o/demo%2Ffruits%2Fstrawberry.webp?alt=media&token=539ff617-f01f-41eb-b66a-51eb0392f8c1"
    ),
    ProductDto(
        id = "123",
        name = "Peaches",
        brand = "SomeBrand",
        description = "Some description goes here",
        price = 9.99,
        category = "fruits",
        tag = "foryou",
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/ecommerce-multimodule.appspot.com/o/demo%2Ffruits%2Fpeaches.webp?alt=media&token=7d24f30c-b09e-4b47-b761-d32766411256"
    )
)