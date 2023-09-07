package dev.havlicektomas.discovery_domain.usecase.favourites

import dev.havlicektomas.discovery_domain.model.Product
import dev.havlicektomas.discovery_domain.repository.FavouriteProductRepository
import dev.havlicektomas.discovery_domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class GetFavouriteProductsUseCase(
    private val repository: FavouriteProductRepository
) {

    operator fun invoke(): Flow<List<Product>> {
        return repository.getFavouriteProducts()
    }
}