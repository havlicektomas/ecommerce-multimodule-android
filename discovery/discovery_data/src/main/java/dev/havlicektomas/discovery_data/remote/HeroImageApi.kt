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
                imageUrl = "https://firebasestorage.googleapis.com/v0/b/ecommerce-multimodule.appspot.com/o/cabbage.webp?alt=media&token=de5c45db-a017-43b1-aae8-9dd0bf9f1085"
            ),
            HeroImageDto(
                id = "2",
                imageUrl = "https://firebasestorage.googleapis.com/v0/b/ecommerce-multimodule.appspot.com/o/cabbage.webp?alt=media&token=de5c45db-a017-43b1-aae8-9dd0bf9f1085"
            ),
            HeroImageDto(
                id = "3",
                imageUrl = "https://firebasestorage.googleapis.com/v0/b/ecommerce-multimodule.appspot.com/o/cabbage.webp?alt=media&token=de5c45db-a017-43b1-aae8-9dd0bf9f1085"
            )
        )
    )
}