package dev.havlicektomas.discovery_domain.usecase

data class ProductUseCases(
    val searchProductsUseCase: SearchProductsUseCase,
    val getProductsUseCase: GetProductsUseCase,
    val getProductByIdUseCase: GetProductByIdUseCase
)
