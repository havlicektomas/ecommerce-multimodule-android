package dev.havlicektomas.discovery_data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.havlicektomas.discovery_data.local.entity.HeroImageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HeroImageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHeroImage(heroImage: HeroImageEntity)

    @Query(
        """
            DELETE
            FROM heroimageentity
        """
    )
    fun deleteHeroImages()

    @Query(
        """
            SELECT *
            FROM heroimageentity
        """
    )
    fun getHeroImages(): Flow<List<HeroImageEntity>>
}