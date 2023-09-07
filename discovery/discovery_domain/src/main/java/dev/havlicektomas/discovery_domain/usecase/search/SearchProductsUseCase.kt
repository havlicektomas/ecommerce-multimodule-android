package dev.havlicektomas.discovery_domain.usecase.search

import dev.havlicektomas.discovery_domain.repository.ProductRepo

class SearchProductsUseCase(
    private val repository: ProductRepo
) {

    operator suspend fun invoke(
        query: String,
        page: Int = 1,
        pageSize: Int = 40
    ) {
        if(query.isNotBlank()) {
            repository.searchProducts(query.trim())
        }
    }
}