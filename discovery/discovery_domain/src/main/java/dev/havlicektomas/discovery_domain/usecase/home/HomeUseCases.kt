package dev.havlicektomas.discovery_domain.usecase.home

import dev.havlicektomas.discovery_domain.usecase.FetchProductsUseCase
import dev.havlicektomas.discovery_domain.usecase.GetProductsUseCase
import dev.havlicektomas.discovery_domain.usecase.ToggleFavouriteProductUseCase

data class HomeUseCases(
    val fetchHeroImagesUseCase: FetchHeroImagesUseCase,
    val fetchProductsUseCase: FetchProductsUseCase,
    val getHeroImagesUseCase: GetHeroImagesUseCase,
    val getProductsUseCase: GetProductsUseCase,
    val toggleFavouriteProductUseCase: ToggleFavouriteProductUseCase
)