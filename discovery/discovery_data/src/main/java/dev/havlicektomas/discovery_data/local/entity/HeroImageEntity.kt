package dev.havlicektomas.discovery_data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HeroImageEntity(
    val heroImageId: String,
    val imageUrl: String,
    val link: String? = null,
    @PrimaryKey val id: Int? = null
)
