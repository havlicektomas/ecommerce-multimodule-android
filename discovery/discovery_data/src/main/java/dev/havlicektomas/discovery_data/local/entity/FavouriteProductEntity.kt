package dev.havlicektomas.discovery_data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

// FavouriteProductEntity is to create separate db table for favourite products
@Entity
data class FavouriteProductEntity(
    val productId: String,
    val name: String,
    val brand: String,
    val description: String,
    val price: Double,
    val category: String,
    val tag: String,
    val imageUrl: String,
    @PrimaryKey val id: Int? = null
)