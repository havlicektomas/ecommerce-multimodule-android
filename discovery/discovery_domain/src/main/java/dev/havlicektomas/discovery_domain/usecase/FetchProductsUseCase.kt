package dev.havlicektomas.discovery_domain.usecase

import dev.havlicektomas.discovery_domain.repository.ProductRepo

class FetchProductsUseCase(
    private val repository: ProductRepo
) {

    suspend operator fun invoke(category: String) {
        repository.fetchProducts(category)
    }
}