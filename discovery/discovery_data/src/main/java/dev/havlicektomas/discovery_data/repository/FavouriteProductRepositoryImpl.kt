package dev.havlicektomas.discovery_data.repository

import dev.havlicektomas.discovery_data.local.FavouriteProductDao
import dev.havlicektomas.discovery_data.mapper.toFavouriteEntity
import dev.havlicektomas.discovery_data.mapper.toProduct
import dev.havlicektomas.discovery_data.mapper.toProductDto
import dev.havlicektomas.discovery_data.remote.ProductApi
import dev.havlicektomas.discovery_data.remote.dto.ProductDto
import dev.havlicektomas.discovery_domain.model.Product
import dev.havlicektomas.discovery_domain.repository.FavouriteProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavouriteProductRepositoryImpl(
    private val productApi: ProductApi,
    private val favouriteProductDao: FavouriteProductDao
): FavouriteProductRepository {
    override suspend fun fetchFavouriteProducts() {
        val response = productApi.getFavouriteProducts()
        if (response.isSuccessful) {
            response.body()?.let { productDtos ->
                deleteAllProducts()
                saveProducts(productDtos)
            }
        }
    }

    override fun getFavouriteProducts(): Flow<List<Product>> {
        return favouriteProductDao.getProducts().map { entityList ->
            entityList.map { favouriteEntity ->
                favouriteEntity.toProduct()
            }
        }
    }

    override suspend fun toggleProductAsFavourite(product: Product) {
        val response = productApi.toggleFavouriteProduct(product.toProductDto())
        if (response.isSuccessful) {
            response.body()?.let { productDtos ->
                deleteAllProducts()
                saveProducts(productDtos)
            }
        }
    }

    private suspend fun saveProducts(products: List<ProductDto>) {
        products.forEach { dto ->
            favouriteProductDao.insertFavouriteProduct(dto.toFavouriteEntity())
        }
    }

    private fun deleteAllProducts() {
        favouriteProductDao.deleteAllFavouriteProducts()
    }
}