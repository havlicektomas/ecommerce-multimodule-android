package dev.havlicektomas.discovery_presentation.favourite

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.havlicektomas.discovery_domain.usecase.ProductUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val productUseCases: ProductUseCases
): ViewModel() {

    private val defaultState = FavouritesScreenState(emptyList())

    val state = MutableStateFlow(defaultState)
}