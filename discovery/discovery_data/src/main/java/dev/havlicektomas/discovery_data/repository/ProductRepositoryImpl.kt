package dev.havlicektomas.discovery_data.repository

import dev.havlicektomas.discovery_data.local.ProductDao
import dev.havlicektomas.discovery_data.mapper.toEntity
import dev.havlicektomas.discovery_data.mapper.toProduct
import dev.havlicektomas.discovery_data.remote.ProductApi
import dev.havlicektomas.discovery_data.remote.dto.ProductDto
import dev.havlicektomas.discovery_domain.model.Product
import dev.havlicektomas.discovery_domain.model.ProductCategory
import dev.havlicektomas.discovery_domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productApi: ProductApi,
    private val productDao: ProductDao
): ProductRepository {
    override suspend fun searchProducts(
        query: String,
        page: Int,
        pageSize: Int
    ) {
        val response = productApi.searchFood(query, page, pageSize)
        val products = response.body()?.products
        if (response.isSuccessful && products != null) {
            deleteAllProducts()
            saveProducts(products)
        }
    }

    override suspend fun fetchProductCategories() {
        TODO("Not yet implemented")
    }

    override fun getProducts(): Flow<List<Product>> {
        return productDao.getProducts().map { entityList ->
            entityList.map { entity ->
                entity.toProduct()
            }
        }
    }

    override fun getProductById(productId: String): Flow<Product> {
        return productDao.getProductById(productId).map { entity ->
            entity.toProduct()
        }
    }

    override fun getProductCategories(): Flow<List<ProductCategory>> {
        TODO("Not yet implemented")
    }

    private suspend fun saveProducts(products: List<ProductDto>) {
        products.forEach { dto ->
            productDao.insertProduct(dto.toEntity())
        }
    }

    private fun deleteAllProducts() {
        productDao.deleteProducts()
    }
}