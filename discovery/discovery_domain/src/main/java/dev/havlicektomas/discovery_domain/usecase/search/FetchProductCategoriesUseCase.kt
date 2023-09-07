package dev.havlicektomas.discovery_domain.usecase.search

import dev.havlicektomas.discovery_domain.repository.ProductCategoryRepo

class FetchProductCategoriesUseCase (
    private val repository: ProductCategoryRepo
) {

    suspend operator fun invoke() {
        repository.fetchProductCategories()
    }
}