package dev.havlicektomas.discovery_presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.havlicektomas.discovery_domain.model.Product
import dev.havlicektomas.discovery_domain.usecase.home.HomeUseCases
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val homeUseCases: HomeUseCases
): ViewModel() {

    companion object {
        const val HOME = "home"
    }

    private val defaultState = HomeScreenState(
        heroImages = emptyList(),
        productCategories = emptyMap()
    )

    val state = combine(
        homeUseCases.getProductsUseCase(HOME),
        homeUseCases.getHeroImagesUseCase()
    ) { products, heroImages ->
        val productCategories = products.groupBy { product ->
            product.tag
        }
        defaultState.copy(
            heroImages = heroImages,
            productCategories = productCategories
        )
    }
    .stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5.seconds.inWholeMilliseconds),
        defaultState
    )

    var selectedProduct by mutableStateOf<Product?>(null)

    init {
        fetchData()
    }

    fun onEvent(event: HomeScreenEvent) {
        when (event) {
            is HomeScreenEvent.OnFavouritesProductClick -> {
                toggleFavouriteProduct(event.product)
            }
            is HomeScreenEvent.OnProductDetailShow -> {
                selectedProduct = event.product
            }
            is HomeScreenEvent.OnProductDetailHide -> {
                selectedProduct = null
            }
        }
    }

    private fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            homeUseCases.fetchHeroImagesUseCase()
        }
        viewModelScope.launch(Dispatchers.IO) {
            homeUseCases.fetchProductsUseCase(HOME)
        }
    }

    private fun toggleFavouriteProduct(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            homeUseCases.toggleFavouriteProductUseCase(product)
        }
    }
}