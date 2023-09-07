package dev.havlicektomas.discovery_domain.repository

import dev.havlicektomas.discovery_domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepo {
    suspend fun fetchProducts(category: String)
    suspend fun searchProducts(query: String)
    fun getProducts(category: String): Flow<List<Product>>
}