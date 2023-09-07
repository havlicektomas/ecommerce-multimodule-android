package dev.havlicektomas.discovery_data.repository

import dev.havlicektomas.discovery_data.local.ProductDao
import dev.havlicektomas.discovery_data.mapper.toEntity
import dev.havlicektomas.discovery_data.mapper.toProduct
import dev.havlicektomas.discovery_data.mapper.toProductDto
import dev.havlicektomas.discovery_data.remote.ProductApi
import dev.havlicektomas.discovery_data.remote.fakeProductsApiResponse
import dev.havlicektomas.discovery_domain.model.Product
import dev.havlicektomas.discovery_domain.repository.ProductRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductRepoImplclass @Inject constructor(
    private val productApi: ProductApi,
    private val productDao: ProductDao
): ProductRepo {
    companion object {
        const val SEARCH_CATEGORY = "search"
        const val FAVOURITE = "favourite"
    }
    override suspend fun fetchProducts(category: String) {
        val response = fakeProductsApiResponse(category)
        val products = response.body()?.products

        if (response.isSuccessful && products != null) {
            productDao.deleteProducts(category)
            products.forEach { productDto ->
                val categoryProductDto = productDto.copy(category = category)
                productDao.insertProduct(categoryProductDto.toEntity())
            }
        }
    }

    override suspend fun searchProducts(query: String) {
        val response = fakeProductsApiResponse(SEARCH_CATEGORY)
        val products = response.body()?.products

        if (response.isSuccessful && products != null) {
            productDao.deleteProducts(SEARCH_CATEGORY)
            products.forEach { productDto ->
                val searchProductDto = productDto.copy(category = SEARCH_CATEGORY)
                productDao.insertProduct(searchProductDto.toEntity())
            }
        }
    }

    override suspend fun toggleFavouriteProduct(product: Product) {
        val isFavourite = product.category == FAVOURITE
        if (isFavourite) {
            productDao.deleteFavouriteProductById(productId = product.id)
        } else {
            val favouriteProduct = product.copy(category = FAVOURITE)
            productDao.insertProduct(favouriteProduct.toEntity())
        }
    }

    override fun getProducts(category: String): Flow<List<Product>> {
        return productDao.getProducts(category).map { entities ->
            entities.map { entity ->
                entity.toProduct()
            }
        }
    }
}