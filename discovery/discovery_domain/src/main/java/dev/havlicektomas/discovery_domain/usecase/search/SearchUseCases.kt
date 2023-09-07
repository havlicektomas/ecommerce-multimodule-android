package dev.havlicektomas.discovery_domain.usecase.search

import dev.havlicektomas.discovery_domain.usecase.GetProductsUseCase

data class SearchUseCases(
    val searchProductsUseCase: SearchProductsUseCase,
    val getProductsUseCase: GetProductsUseCase,
    val fetchProductCategoriesUseCase: FetchProductCategoriesUseCase,
    val getProductCategoriesUseCase: GetProductCategoriesUseCase
)
