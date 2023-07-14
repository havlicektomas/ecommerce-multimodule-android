package dev.havlicektomas.discovery_presentation.home

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
class HomeScreenViewModel @Inject constructor(
    private val productUseCases: SearchProductUseCases
): ViewModel() {

    private val defaultState = HomeScreenState(
        heroImageUrls = emptyList(),
        productCategories = emptyMap()
    )

    val state: StateFlow<HomeScreenState> = productUseCases.getProductsUseCase()
        .map { allProducts ->
            val productCategories = allProducts.groupBy { product ->
                product.tag
            }
            defaultState.copy(productCategories = productCategories)
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5.seconds.inWholeMilliseconds),
            defaultState
        )

    init {
        searchProducts("home-default")
    }

    private fun searchProducts(
        query: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            productUseCases.searchProductsUseCase(query)
        }
    }
}