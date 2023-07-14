package dev.havlicektomas.discovery_presentation.favourite

import dev.havlicektomas.discovery_domain.model.Product

sealed class FavouritesScreenEvent {
    data class OnFavouritesProductClick(val product: Product): FavouritesScreenEvent()
}
