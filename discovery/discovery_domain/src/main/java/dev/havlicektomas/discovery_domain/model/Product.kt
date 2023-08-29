package dev.havlicektomas.discovery_domain.model

data class Product(
    val id: String,
    val name: String,
    val brand: String,
    val description: String,
    val price: Double,
    val category: String,
    val tag: String,
    val imageUrl: String
)
