package dev.havlicektomas.discovery_presentation.home

import dev.havlicektomas.discovery_domain.model.Product

data class HomeScreenState(
    val heroImageUrls: List<String>,
    val productCategories: Map<String, List<Product>>
)