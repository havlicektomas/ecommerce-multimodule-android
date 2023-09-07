package dev.havlicektomas.discovery_data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.havlicektomas.discovery_data.local.entity.ProductCategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductCategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(productCategoryEntity: ProductCategoryEntity)

    @Query(
        """
            DELETE
            FROM productcategoryentity
        """
    )
    fun deleteProductCategories()

    @Query(
        """
            SELECT *
            FROM productcategoryentity
        """
    )
    fun getProductCategories(): Flow<List<ProductCategoryEntity>>
}