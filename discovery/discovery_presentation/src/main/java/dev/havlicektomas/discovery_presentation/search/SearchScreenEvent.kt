package dev.havlicektomas.discovery_presentation.search

sealed class SearchScreenEvent {

    data class OnSearchInputChanged(val input: String): SearchScreenEvent()
    data class OnSearchIconClick(val term: String): SearchScreenEvent()
    data class OnCategoryClick(val category: String): SearchScreenEvent()
}