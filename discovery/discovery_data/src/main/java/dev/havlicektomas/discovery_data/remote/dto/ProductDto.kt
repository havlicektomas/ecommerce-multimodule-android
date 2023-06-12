package dev.havlicektomas.discovery_data.remote.dto

data class ProductDto(
    val id: String,
    val name: String,
    val brand: String,
    val description: String,
    val price: Double,
    val category: String,
    val tag: String
)

data class ProductSearchDto(
    val page: Int,
    val pageSize: Int,
    val products: List<ProductDto>
)
