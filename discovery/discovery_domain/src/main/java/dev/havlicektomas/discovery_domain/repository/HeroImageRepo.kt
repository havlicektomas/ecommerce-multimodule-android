package dev.havlicektomas.discovery_domain.repository

import dev.havlicektomas.discovery_domain.model.HeroImage
import kotlinx.coroutines.flow.Flow

interface HeroImageRepo {
    suspend fun fetchHeroImages(

    )
    fun getHeroImages(): Flow<List<HeroImage>>
}