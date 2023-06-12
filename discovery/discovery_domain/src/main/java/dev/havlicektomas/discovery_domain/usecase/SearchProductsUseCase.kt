package dev.havlicektomas.discovery_domain.usecase

import dev.havlicektomas.discovery_domain.repository.ProductRepository

class SearchProductsUseCase(
    private val repository: ProductRepository
) {

    suspend operator fun invoke(
        query: String,
        page: Int = 1,
        pageSize: Int = 40
    ) {
        if(query.isNotBlank()) {
            repository.searchProducts(query.trim(), page, pageSize)
        }
    }
}