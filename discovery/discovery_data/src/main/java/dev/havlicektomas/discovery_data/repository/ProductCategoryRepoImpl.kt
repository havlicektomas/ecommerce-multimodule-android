package dev.havlicektomas.discovery_data.repository

import dev.havlicektomas.discovery_data.local.dao.ProductCategoryDao
import dev.havlicektomas.discovery_data.mapper.toEntity
import dev.havlicektomas.discovery_data.mapper.toProductCategory
import dev.havlicektomas.discovery_data.remote.ProductCategoryApi
import dev.havlicektomas.discovery_data.remote.fakeProductCategoriesApiResponse
import dev.havlicektomas.discovery_domain.model.ProductCategory
import dev.havlicektomas.discovery_domain.repository.ProductCategoryRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductCategoryRepoImpl @Inject constructor(
    private val productCategoryApi: ProductCategoryApi,
    private val productCategoryDao: ProductCategoryDao
): ProductCategoryRepo {
    override suspend fun fetchProductCategories() {
        val response = fakeProductCategoriesApiResponse()

        if (response.isSuccessful) {
            productCategoryDao.deleteProductCategories()
            response.body()?.forEach { categoryDto ->
                productCategoryDao.insertCategory(categoryDto.toEntity())
            }
        }
    }

    override fun getProductCategories(): Flow<List<ProductCategory>> {
        return productCategoryDao.getProductCategories().map { entities ->
            entities.map { entity ->
                entity.toProductCategory()
            }
        }
    }
}