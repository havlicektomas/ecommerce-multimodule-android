package dev.havlicektomas.discovery_data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductEntity(
    val productId: String,
    val name: String,
    val brand: String,
    val description: String,
    val price: Double,
    val category: String,
    val tag: String,
    @PrimaryKey val id: Int? = null
)