package dev.havlicektomas.discovery_presentation.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.havlicektomas.discovery_domain.model.Product
import dev.havlicektomas.discovery_domain.usecase.favourites.FavouriteProductUseCases
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val favouriteProductsUseCases: FavouriteProductUseCases
): ViewModel() {

    private val defaultState = FavouritesScreenState(emptyList())

    val state: StateFlow<FavouritesScreenState> = favouriteProductsUseCases.getFavouriteProductsUseCase().map { favourites ->
        defaultState.copy(favouriteProducts = favourites)
    }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5.seconds.inWholeMilliseconds),
            defaultState
        )

    init {
        fetchFavourites()
    }

    fun onEvent(event: FavouritesScreenEvent) {
        when (event) {
            is FavouritesScreenEvent.OnFavouritesProductClick -> {
                val product = event.product
                toggleFavouriteProduct(product)
            }
        }
    }

    private fun fetchFavourites() {
        viewModelScope.launch(Dispatchers.IO) {
            favouriteProductsUseCases.fetchFavouriteProducts()
        }
    }

    private fun toggleFavouriteProduct(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            favouriteProductsUseCases.toggleFavouriteProductUseCase(product)
        }
    }
}