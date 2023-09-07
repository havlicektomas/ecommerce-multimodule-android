package dev.havlicektomas.discovery_domain.usecase

import dev.havlicektomas.discovery_domain.model.Product
import dev.havlicektomas.discovery_domain.repository.ProductRepo

class ToggleFavouriteProductUseCase(
    private val repository: ProductRepo
) {

    suspend operator fun invoke(product: Product) {
        repository.toggleFavouriteProduct(product)
    }
}