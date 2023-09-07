package dev.havlicektomas.discovery_domain.usecase.favourites

import dev.havlicektomas.discovery_domain.usecase.FetchProductsUseCase
import dev.havlicektomas.discovery_domain.usecase.GetProductsUseCase
import dev.havlicektomas.discovery_domain.usecase.ToggleFavouriteProductUseCase

data class FavouriteUseCases(
    val fetchProductsUseCase: FetchProductsUseCase,
    val getProductsUseCase: GetProductsUseCase,
    val toggleFavouriteProductUseCase: ToggleFavouriteProductUseCase
)
