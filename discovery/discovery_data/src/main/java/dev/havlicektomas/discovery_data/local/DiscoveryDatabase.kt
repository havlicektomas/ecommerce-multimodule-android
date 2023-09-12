package dev.havlicektomas.discovery_data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.havlicektomas.discovery_data.local.dao.HeroImageDao
import dev.havlicektomas.discovery_data.local.dao.ProductCategoryDao
import dev.havlicektomas.discovery_data.local.dao.ProductDao
import dev.havlicektomas.discovery_data.local.entity.HeroImageEntity
import dev.havlicektomas.discovery_data.local.entity.ProductCategoryEntity
import dev.havlicektomas.discovery_data.local.entity.ProductEntity

@Database(
    entities = [ProductEntity::class, ProductCategoryEntity::class, HeroImageEntity::class],
    version = 1,
    exportSchema = false
)
abstract class DiscoveryDatabase: RoomDatabase() {

    abstract val productDao: ProductDao
    abstract val productCategoryDao: ProductCategoryDao
    abstract val heroImageDao: HeroImageDao
}