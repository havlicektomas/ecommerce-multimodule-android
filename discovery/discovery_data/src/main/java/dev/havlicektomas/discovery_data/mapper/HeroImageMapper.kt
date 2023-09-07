package dev.havlicektomas.discovery_data.mapper

import dev.havlicektomas.discovery_data.local.entity.HeroImageEntity
import dev.havlicektomas.discovery_data.remote.dto.HeroImageDto
import dev.havlicektomas.discovery_domain.model.HeroImage

fun HeroImageDto.toEntity() = HeroImageEntity(
    heroImageId = id,
    imageUrl = imageUrl,
    link = link
)

fun HeroImageEntity.toHeroImage() = HeroImage(
    id = heroImageId,
    imageUrl = imageUrl,
    link = link
)