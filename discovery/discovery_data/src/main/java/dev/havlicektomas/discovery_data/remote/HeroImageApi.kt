package dev.havlicektomas.discovery_data.remote

import dev.havlicektomas.discovery_data.remote.dto.HeroImageDto
import dev.havlicektomas.discovery_data.remote.dto.ProductCategoryDto
import dev.havlicektomas.discovery_domain.model.ProductCategory
import kotlinx.coroutines.delay
import retrofit2.Response
import retrofit2.http.GET
import kotlin.time.Duration.Companion.seconds

interface HeroImageApi {
    @GET("heroimages")
    suspend fun getHeroImages(): Response<List<HeroImageDto>>

    companion object {
        const val BASE_URL = "https://api.example.com/"
    }
}

suspend fun fakeHeroImageApiResponse():Response<List<HeroImageDto>> {
    delay(1.seconds)
    return Response.success(
        listOf(
            HeroImageDto(
                id = "1",
                imageUrl = "https://firebasestorage.googleapis.com/v0/b/ecommerce-multimodule.appspot.com/o/demo%2Ffruits%2Fblueberry.webp?alt=media&token=e8fa83ee-c731-42fb-8c96-2684db784b1b"
            ),
            HeroImageDto(
                id = "2",
                imageUrl = "https://firebasestorage.googleapis.com/v0/b/ecommerce-multimodule.appspot.com/o/demo%2Ffruits%2Fraspberry.webp?alt=media&token=469ee627-6e3c-4445-9fc5-20a21d5c6a38"
            ),
            HeroImageDto(
                id = "3",
                imageUrl = "https://firebasestorage.googleapis.com/v0/b/ecommerce-multimodule.appspot.com/o/demo%2Fveggies%2Fradishes.webp?alt=media&token=c1c11568-a449-4cbc-b4f5-d3d4ff3a311b"
            )
        )
    )
}