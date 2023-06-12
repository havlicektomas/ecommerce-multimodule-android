package dev.havlicektomas.discovery_domain.repository

import dev.havlicektomas.discovery_domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    suspend fun searchProducts(
        query: String,
        page: Int,
        pageSize: Int
    )

    fun getProducts(): Flow<List<Product>>

    fun getProductById(productId: String): Flow<Product>
}