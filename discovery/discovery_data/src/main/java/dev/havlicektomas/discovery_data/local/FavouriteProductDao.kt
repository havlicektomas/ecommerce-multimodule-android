package dev.havlicektomas.discovery_data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.havlicektomas.discovery_data.local.entity.FavouriteProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavouriteProduct(productEntity: FavouriteProductEntity)

    @Delete
    suspend fun deleteFavouriteProduct(productEntity: FavouriteProductEntity)

    @Query(
        """
            DELETE
            FROM favouriteproductentity
        """
    )
    fun deleteAllFavouriteProducts()

    @Query(
        """
            SELECT *
            FROM favouriteproductentity
        """
    )
    fun getProducts(): Flow<List<FavouriteProductEntity>>
}