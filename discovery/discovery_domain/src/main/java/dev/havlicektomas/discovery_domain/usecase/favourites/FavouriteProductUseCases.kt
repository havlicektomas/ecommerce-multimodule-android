package dev.havlicektomas.discovery_domain.usecase.favourites

import dev.havlicektomas.discovery_domain.usecase.FetchFavouriteProductsUseCase
import dev.havlicektomas.discovery_domain.usecase.GetFavouriteProductsUseCase
import dev.havlicektomas.discovery_domain.usecase.ToggleFavouriteProductUseCase

data class FavouriteProductUseCases(
    val fetchFavouriteProducts: FetchFavouriteProductsUseCase,
    val getFavouriteProductsUseCase: GetFavouriteProductsUseCase,
    val toggleFavouriteProductUseCase: ToggleFavouriteProductUseCase
)
