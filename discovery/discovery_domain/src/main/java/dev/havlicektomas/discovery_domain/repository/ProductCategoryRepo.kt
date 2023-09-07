package dev.havlicektomas.discovery_domain.repository

import dev.havlicektomas.discovery_domain.model.ProductCategory
import kotlinx.coroutines.flow.Flow

interface ProductCategoryRepo {
    suspend fun fetchProductCategories()
    fun getProductCategories(): Flow<List<ProductCategory>>
}