package dev.havlicektomas.discovery_data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.havlicektomas.discovery_data.local.entity.FavouriteProductEntity
import dev.havlicektomas.discovery_data.local.entity.ProductCategoryEntity
import dev.havlicektomas.discovery_data.local.entity.ProductEntity

@Database(
    entities = [ProductEntity::class, ProductCategoryEntity::class, FavouriteProductEntity::class],
    version = 1
)
abstract class DiscoveryDatabase: RoomDatabase() {

    abstract val productDao: ProductDao
    abstract val favouriteProductDao: FavouriteProductDao
}