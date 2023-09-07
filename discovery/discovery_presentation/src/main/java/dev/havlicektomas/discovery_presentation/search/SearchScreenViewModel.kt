package dev.havlicektomas.discovery_presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.havlicektomas.discovery_domain.usecase.search.SearchUseCases
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val productUseCases: SearchUseCases
): ViewModel() {

    private val defaultState = SearchScreenState("", emptyList(), emptyList())

    private val _state = MutableStateFlow(defaultState)
    val state = combine(
        _state,
        productUseCases.getProductCategoriesUseCase()
    ) { uiState, categories ->
        uiState.copy(productCategories = categories)
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
            is SearchScreenEvent.OnSearchInputChanged -> {
                _state.update {
                    it.copy(searchInput = event.input)
                }
            }
            is SearchScreenEvent.OnSearchIconClick -> { searchProducts("query") }
            is SearchScreenEvent.OnCategoryClick -> { searchProducts("category-name") }
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