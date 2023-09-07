package dev.havlicektomas.discovery_domain.usecase.favourites

data class FavouriteProductUseCases(
    val fetchFavouriteProducts: FetchFavouriteProductsUseCase,
    val getFavouriteProductsUseCase: GetFavouriteProductsUseCase,
    val toggleFavouriteProductUseCase: ToggleFavouriteProductUseCase
)
