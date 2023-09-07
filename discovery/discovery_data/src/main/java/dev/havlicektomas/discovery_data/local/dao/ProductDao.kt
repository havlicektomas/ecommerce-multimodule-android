package dev.havlicektomas.discovery_data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.havlicektomas.discovery_data.local.entity.ProductCategoryEntity
import dev.havlicektomas.discovery_data.local.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(productEntity: ProductEntity)

    @Query(
        """
            DELETE
            FROM productentity
            WHERE category == :category
        """
    )
    fun deleteProducts(category: String)

    @Query(
        """
            DELETE
            FROM productentity
            WHERE category == 'favourite' AND productId == :productId
        """
    )
    fun deleteFavouriteProductById(productId: String)

    @Query(
        """
            SELECT *
            FROM productentity
            WHERE category == :category
        """
    )
    fun getProducts(category: String): Flow<List<ProductEntity>>

    @Query(
        """
            SELECT *
            FROM productentity
            WHERE productId == :productId
        """
    )
    fun getProductById(productId: String): Flow<ProductEntity>
}