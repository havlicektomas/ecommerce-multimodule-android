package dev.havlicektomas.discovery_presentation.search

import dev.havlicektomas.discovery_domain.model.Product
import dev.havlicektomas.discovery_domain.model.ProductCategory

data class SearchScreenState(
    val searchInput: String,
    val productCategories: List<ProductCategory>,
    val productResults: List<Product>,
    val isSearching: Boolean = false
)
