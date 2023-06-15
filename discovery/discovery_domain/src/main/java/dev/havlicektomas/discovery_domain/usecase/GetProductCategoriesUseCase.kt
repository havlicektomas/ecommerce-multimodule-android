package dev.havlicektomas.discovery_domain.usecase

import dev.havlicektomas.discovery_domain.model.ProductCategory
import dev.havlicektomas.discovery_domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class GetProductCategoriesUseCase(
    private val repository: ProductRepository
) {

    operator fun invoke(): Flow<List<ProductCategory>> {
        return repository.getProductCategories()
    }
}