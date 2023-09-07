package dev.havlicektomas.discovery_domain.usecase.favourites

import dev.havlicektomas.discovery_domain.model.Product
import dev.havlicektomas.discovery_domain.repository.FavouriteProductRepository

class ToggleFavouriteProductUseCase(
    private val repository: FavouriteProductRepository
) {

    suspend operator fun invoke(
        product: Product
    ) {
        repository.toggleProductAsFavourite(product)
    }
}