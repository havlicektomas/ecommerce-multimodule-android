package dev.havlicektomas.discovery_presentation.home

import dev.havlicektomas.discovery_domain.model.Product

sealed class HomeScreenEvent {
    data class OnFavouritesProductClick(val product: Product): HomeScreenEvent()
}