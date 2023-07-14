package dev.havlicektomas.discovery_domain.repository

import dev.havlicektomas.discovery_domain.model.Product
import kotlinx.coroutines.flow.Flow

interface FavouriteProductRepository {

    suspend fun fetchFavouriteProducts()

    fun getFavouriteProducts(): Flow<List<Product>>

    suspend fun toggleProductAsFavourite(product: Product)
}