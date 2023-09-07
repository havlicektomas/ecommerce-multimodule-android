package dev.havlicektomas.discovery_domain.usecase.search

import dev.havlicektomas.discovery_domain.model.ProductCategory
import dev.havlicektomas.discovery_domain.repository.ProductCategoryRepo
import kotlinx.coroutines.flow.Flow

class GetProductCategoriesUseCase(
    private val repository: ProductCategoryRepo
) {

    operator fun invoke(): Flow<List<ProductCategory>> {
        return repository.getProductCategories()
    }
}