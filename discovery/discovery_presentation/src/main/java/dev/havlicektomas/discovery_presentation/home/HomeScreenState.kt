package dev.havlicektomas.discovery_presentation.home

import dev.havlicektomas.discovery_domain.model.HeroImage
import dev.havlicektomas.discovery_domain.model.Product

data class HomeScreenState(
    val heroImages: List<HeroImage>,
    val productCategories: Map<String, List<Product>>
)