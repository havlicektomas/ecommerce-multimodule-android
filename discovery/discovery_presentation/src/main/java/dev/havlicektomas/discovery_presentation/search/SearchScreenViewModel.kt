package dev.havlicektomas.discovery_presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.havlicektomas.discovery_domain.usecase.search.SearchProductUseCases
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val productUseCases: SearchProductUseCases
): ViewModel() {

    private val defaultState = SearchScreenState("", "", emptyList())

    val state: StateFlow<SearchScreenState> = productUseCases.getProductCategoriesUseCase().map { allCategories ->
        defaultState.copy(productCategories = allCategories)
    }
    .stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5.seconds.inWholeMilliseconds),
        defaultState
    )

    init {
        loadCategories()
    }

    fun onEvent(event: SearchScreenEvent) {
        when (event) {
            is SearchScreenEvent.onSearchInputChanged -> { searchProducts("query") }
            is SearchScreenEvent.onCategoryClick -> { searchProducts("category-name") }
        }
    }

    private fun loadCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            productUseCases.fetchProductCategoriesUseCase()
        }
    }

    private fun searchProducts(
        query: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            productUseCases.searchProductsUseCase(query)
        }
    }
}