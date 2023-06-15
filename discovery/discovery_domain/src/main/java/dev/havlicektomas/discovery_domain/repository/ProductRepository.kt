package dev.havlicektomas.discovery_domain.repository

import dev.havlicektomas.discovery_domain.model.Product
import dev.havlicektomas.discovery_domain.model.ProductCategory
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    suspend fun searchProducts(
        query: String,
        page: Int,
        pageSize: Int
    )

    suspend fun fetchProductCategories()

    fun getProducts(): Flow<List<Product>>

    fun getProductById(productId: String): Flow<Product>

    fun getProductCategories(): Flow<List<ProductCategory>>
}