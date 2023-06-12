package dev.havlicektomas.discovery_presentation.home

sealed class HomeScreenEvent {
    object OnHeroImageClick: HomeScreenEvent()
    object OnProductListItemClick: HomeScreenEvent()
}