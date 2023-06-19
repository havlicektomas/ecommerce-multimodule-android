package dev.havlicektomas.discovery_presentation.favourite

import dev.havlicektomas.discovery_domain.model.Product

data class FavouritesScreenState(
    val favouriteProducts: List<Product>
)
