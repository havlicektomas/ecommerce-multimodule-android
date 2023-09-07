package dev.havlicektomas.discovery_data.repository

import dev.havlicektomas.discovery_data.local.dao.HeroImageDao
import dev.havlicektomas.discovery_data.mapper.toEntity
import dev.havlicektomas.discovery_data.mapper.toHeroImage
import dev.havlicektomas.discovery_data.mapper.toProductCategory
import dev.havlicektomas.discovery_data.remote.HeroImageApi
import dev.havlicektomas.discovery_data.remote.fakeHeroImageApiResponse
import dev.havlicektomas.discovery_data.remote.fakeProductCategoriesApiResponse
import dev.havlicektomas.discovery_domain.model.HeroImage
import dev.havlicektomas.discovery_domain.repository.HeroImageRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HeroImageRepoImpl @Inject constructor(
    private val heroImageApi: HeroImageApi,
    private val heroImageDao: HeroImageDao
): HeroImageRepo {
    override suspend fun fetchHeroImages() {
        val response = fakeHeroImageApiResponse()

        if (response.isSuccessful) {
            heroImageDao.deleteHeroImages()
            response.body()?.forEach { heroImageDto ->
                heroImageDao.insertHeroImage(heroImageDto.toEntity())
            }
        }
    }
    override fun getHeroImages(): Flow<List<HeroImage>> {
        return heroImageDao.getHeroImages().map { entities ->
            entities.map { entity ->
                entity.toHeroImage()
            }
        }
    }
}