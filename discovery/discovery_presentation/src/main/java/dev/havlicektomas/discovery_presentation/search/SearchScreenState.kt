package dev.havlicektomas.discovery_presentation.search

import dev.havlicektomas.discovery_domain.model.ProductCategory

data class SearchScreenState(
    val searchInput: String,
    val searchPlaceHolder: String,
    val productCategories: List<ProductCategory>
)
