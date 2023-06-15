package dev.havlicektomas.discovery_presentation.search

sealed class SearchScreenEvent {

    object onSearchInputChanged: SearchScreenEvent()
    object onCategoryClick: SearchScreenEvent()
}