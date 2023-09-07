package dev.havlicektomas.discovery_domain.usecase.home

import dev.havlicektomas.discovery_domain.model.HeroImage
import dev.havlicektomas.discovery_domain.repository.HeroImageRepo
import kotlinx.coroutines.flow.Flow

class GetHeroImagesUseCase(
    private val repository: HeroImageRepo
) {
    operator fun invoke(): Flow<List<HeroImage>> {
        return repository.getHeroImages()
    }
}