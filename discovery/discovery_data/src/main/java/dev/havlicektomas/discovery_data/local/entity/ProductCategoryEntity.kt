package dev.havlicektomas.discovery_data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.havlicektomas.discovery_data.remote.dto.ProductDto

@Entity
data class ProductCategoryEntity(
    val name: String,
    val imageUrl: String,
    val productsQuery: String,
    @PrimaryKey val id: Int? = null
)
