package dev.havlicektomas.discovery_domain.usecase

import dev.havlicektomas.discovery_domain.repository.FavouriteProductRepository

class FetchFavouriteProductsUseCase(
    private val repository: FavouriteProductRepository
) {

    suspend operator fun invoke() {
        repository.fetchFavouriteProducts()
    }
}