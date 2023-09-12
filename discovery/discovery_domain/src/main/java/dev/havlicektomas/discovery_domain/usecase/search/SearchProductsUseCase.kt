package dev.havlicektomas.discovery_domain.usecase.search

import dev.havlicektomas.discovery_domain.repository.ProductRepo

class SearchProductsUseCase(
    private val repository: ProductRepo
) {

    suspend operator fun invoke(query: String) {
        if(query.isNotBlank()) {
            repository.searchProducts(query.trim())
        }
    }
}