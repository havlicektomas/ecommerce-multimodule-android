package dev.havlicektomas.discovery_data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.havlicektomas.discovery_data.local.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(productEntity: ProductEntity)

    @Delete
    suspend fun deleteProduct(productEntity: ProductEntity)

    @Query(
        """
            DELETE
            FROM productentity
        """
    )
    fun deleteProducts()

    @Query(
        """
            SELECT *
            FROM productentity
        """
    )
    fun getProducts(): Flow<List<ProductEntity>>

    @Query(
        """
            SELECT *
            FROM productentity
            WHERE productId == :productId
        """
    )
    fun getProductById(productId: String): Flow<ProductEntity>
}