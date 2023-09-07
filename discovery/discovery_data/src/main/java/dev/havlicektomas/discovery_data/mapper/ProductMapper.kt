package dev.havlicektomas.discovery_data.mapper

import dev.havlicektomas.discovery_data.local.entity.FavouriteProductEntity
import dev.havlicektomas.discovery_data.local.entity.ProductCategoryEntity
import dev.havlicektomas.discovery_data.local.entity.ProductEntity
import dev.havlicektomas.discovery_data.remote.dto.ProductCategoryDto
import dev.havlicektomas.discovery_data.remote.dto.ProductDto
import dev.havlicektomas.discovery_domain.model.Product
import dev.havlicektomas.discovery_domain.model.ProductCategory

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

fun ProductCategoryDto.toEntity() = ProductCategoryEntity(
    name = name,
    imageUrl = imageUrl,
    productsQuery = productsQuery
)

fun ProductCategoryEntity.toProductCategory() = ProductCategory(
    name = name,
    imageUrl = imageUrl,
    productsQuery = productsQuery
)

fun ProductDto.toFavouriteEntity() = FavouriteProductEntity(
    productId = id,
    name = name,
    brand = brand,
    description = description,
    price = price,
    category = category,
    tag = tag,
    imageUrl = imageUrl
)

fun FavouriteProductEntity.toProduct() = Product(
    id = productId,
    name = name,
    brand = brand,
    description = description,
    price = price,
    category = category,
    tag = tag,
    imageUrl = imageUrl
)