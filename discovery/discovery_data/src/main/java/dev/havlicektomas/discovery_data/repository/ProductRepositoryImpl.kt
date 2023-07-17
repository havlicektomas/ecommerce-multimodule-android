package dev.havlicektomas.discovery_data.repository

import dev.havlicektomas.discovery_data.local.ProductDao
import dev.havlicektomas.discovery_data.mapper.toEntity
import dev.havlicektomas.discovery_data.mapper.toProduct
import dev.havlicektomas.discovery_data.mapper.toProductCategory
import dev.havlicektomas.discovery_data.remote.ProductApi
import dev.havlicektomas.discovery_data.remote.dto.ProductCategoryDto
import dev.havlicektomas.discovery_data.remote.dto.ProductDto
import dev.havlicektomas.discovery_data.remote.dto.ProductSearchDto
import dev.havlicektomas.discovery_domain.model.Product
import dev.havlicektomas.discovery_domain.model.ProductCategory
import dev.havlicektomas.discovery_domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.Response
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
        //val response = productApi.searchFood(query, page, pageSize)
        val response = fakeProductApiResponse()
        val products = response.body()?.products

        if (response.isSuccessful && products != null) {
            deleteAllProducts()
            saveProducts(products)
        }
    }

    override suspend fun fetchProductCategories() {
        val response = fakeProductCategoriesApiResponse()
        val categories = response.body()

        if (response.isSuccessful && categories != null) {
            deleteAllProductCategories()
            saveProductCategories(categories)
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

    override fun getProductCategories(): Flow<List<ProductCategory>> {
        return productDao.getProductCategories().map { entities ->
            entities.map { entity ->
                entity.toProductCategory()
            }
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

    private suspend fun saveProductCategories(categories: List<ProductCategoryDto>) {
        categories.forEach { dto ->
            productDao.insertCategory(dto.toEntity())
        }
    }

    private fun deleteAllProductCategories() {
        productDao.deleteProductCategories()
    }

    private fun fakeProductCategoriesApiResponse() = Response.success(
        listOf(
            ProductCategoryDto("Category 1", "", "category1"),
            ProductCategoryDto("Category 2", "", "category2"),
            ProductCategoryDto("Category 3", "", "category3"),
            ProductCategoryDto("Category 4", "", "category4"),
            ProductCategoryDto("Category 5", "", "category5"),
            ProductCategoryDto("Category 6", "", "category6"),
        )
    )

    private fun fakeProductApiResponse() = Response.success(
        ProductSearchDto(
            page = 1,
            pageSize = 9,
            products = listOf(
                fakeFeaturedProductDto(), fakeFeaturedProductDto(), fakeFeaturedProductDto(),
                fakeOnsaleProductDto(), fakeOnsaleProductDto(), fakeOnsaleProductDto(),
                fakeForyouProductDto(), fakeForyouProductDto(), fakeForyouProductDto(),
            )
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