package dev.havlicektomas.discovery_domain.usecase

import dev.havlicektomas.discovery_domain.repository.ProductRepository

class FetchProductCategoriesUseCase (
    private val repository: ProductRepository
) {

    suspend operator fun invoke() {
        repository.fetchProductCategories()
    }
}