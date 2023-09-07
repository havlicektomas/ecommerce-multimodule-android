package dev.havlicektomas.discovery_domain.usecase.search

import dev.havlicektomas.discovery_domain.usecase.FetchProductCategoriesUseCase
import dev.havlicektomas.discovery_domain.usecase.GetProductByIdUseCase
import dev.havlicektomas.discovery_domain.usecase.GetProductCategoriesUseCase
import dev.havlicektomas.discovery_domain.usecase.GetProductsUseCase
import dev.havlicektomas.discovery_domain.usecase.SearchProductsUseCase

data class SearchProductUseCases(
    val searchProductsUseCase: SearchProductsUseCase,
    val fetchProductsUseCase: String,
    val getProductsUseCase: GetProductsUseCase,
    val fetchProductCategoriesUseCase: FetchProductCategoriesUseCase,
    val getProductCategoriesUseCase: GetProductCategoriesUseCase
)
