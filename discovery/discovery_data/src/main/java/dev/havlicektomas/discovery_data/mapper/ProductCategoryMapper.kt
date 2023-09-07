package dev.havlicektomas.discovery_data.mapper

import dev.havlicektomas.discovery_data.local.entity.ProductCategoryEntity
import dev.havlicektomas.discovery_data.remote.dto.ProductCategoryDto
import dev.havlicektomas.discovery_domain.model.ProductCategory

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