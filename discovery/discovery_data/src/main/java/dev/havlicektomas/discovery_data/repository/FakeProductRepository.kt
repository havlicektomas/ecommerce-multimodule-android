package dev.havlicektomas.discovery_data.repository

import dev.havlicektomas.discovery_data.local.ProductDao
import dev.havlicektomas.discovery_data.mapper.toEntity
import dev.havlicektomas.discovery_data.mapper.toProduct
import dev.havlicektomas.discovery_data.remote.dto.ProductDto
import dev.havlicektomas.discovery_data.remote.dto.ProductSearchDto
import dev.havlicektomas.discovery_domain.model.Product
import dev.havlicektomas.discovery_domain.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FakeProductRepository @Inject constructor(
    private val productDao: ProductDao
): ProductRepository {
    override suspend fun searchProducts(
        query: String,
        page: Int,
        pageSize: Int
    ) {
        val response = fakeProductApiResponse()
        with(Dispatchers.IO) {
            deleteAllProducts()
            saveProducts(response.products)
        }
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

    private suspend fun saveProducts(products: List<ProductDto>) {
        products.forEach { dto ->
            productDao.insertProduct(dto.toEntity())
        }
    }

    private fun deleteAllProducts() {
        productDao.deleteProducts()
    }

    private fun fakeProductApiResponse() = ProductSearchDto(
        page = 1,
        pageSize = 9,
        products = listOf(
            fakeFeaturedProductDto(), fakeFeaturedProductDto(), fakeFeaturedProductDto(),
            fakeOnsaleProductDto(), fakeOnsaleProductDto(), fakeOnsaleProductDto(),
            fakeForyouProductDto(), fakeForyouProductDto(), fakeForyouProductDto(),
        )
    )

    private fun fakeFeaturedProductDto() = ProductDto(
        id = "123",
        name = "Product 1",
        brand = "SomeBrand",
        description = "Some description goes here",
        price = 9.99,
        category = "Category1",
        tag = "featured"
    )

    private fun fakeOnsaleProductDto() = ProductDto(
        id = "123",
        name = "Product 1",
        brand = "SomeBrand",
        description = "Some description goes here",
        price = 9.99,
        category = "Category1",
        tag = "onsale"
    )

    private fun fakeForyouProductDto() = ProductDto(
        id = "123",
        name = "Product 1",
        brand = "SomeBrand",
        description = "Some description goes here",
        price = 9.99,
        category = "Category1",
        tag = "foryou"
    )
}