package dev.havlicektomas.discovery_data.mapper

import dev.havlicektomas.discovery_data.local.entity.ProductEntity
import dev.havlicektomas.discovery_data.remote.dto.ProductDto
import dev.havlicektomas.discovery_domain.model.Product

fun ProductDto.toEntity() = ProductEntity(
    productId = id,
    name = name,
    brand = brand,
    description = description,
    price = price,
    category = category,
    tag = tag,
    imageUrl = imageUrl
)

fun ProductEntity.toProduct() = Product(
    id = productId,
    name = name,
    brand = brand,
    description = description,
    price = price,
    category = category,
    tag = tag,
    imageUrl = imageUrl
)

fun Product.toProductDto() = ProductDto(
    id = id,
    name = name,
    brand = brand,
    description = description,
    price = price,
    category = category,
    tag = tag,
    imageUrl = imageUrl
)

fun Product.toEntity() = ProductEntity(
    productId = id,
    name = name,
    brand = brand,
    description = description,
    price = price,
    category = category,
    tag = tag,
    imageUrl = imageUrl
)