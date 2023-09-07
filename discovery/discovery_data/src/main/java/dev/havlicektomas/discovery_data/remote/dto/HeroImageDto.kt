package dev.havlicektomas.discovery_data.remote.dto

data class HeroImageDto(
    val id: String,
    val imageUrl: String,
    val link: String? = null
)
