package dev.havlicektomas.discovery_domain.usecase

import dev.havlicektomas.discovery_domain.model.Product
import dev.havlicektomas.discovery_domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class SearchProductsUseCase(
    private val repository: ProductRepository
) {

    operator suspend fun invoke(
        query: String,
        page: Int = 1,
        pageSize: Int = 40
    ) {
        if(query.isNotBlank()) {
            repository.searchProducts(query.trim(), page, pageSize)
        }
    }
}