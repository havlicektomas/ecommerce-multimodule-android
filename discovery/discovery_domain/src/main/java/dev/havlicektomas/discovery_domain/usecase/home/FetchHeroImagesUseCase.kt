package dev.havlicektomas.discovery_domain.usecase.home

import dev.havlicektomas.discovery_domain.repository.HeroImageRepo

class FetchHeroImagesUseCase(
    private val repository: HeroImageRepo
) {
    suspend operator fun invoke() {
        repository.fetchHeroImages()
    }
}