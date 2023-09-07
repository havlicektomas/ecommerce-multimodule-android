package dev.havlicektomas.discovery_domain.usecase

import dev.havlicektomas.discovery_domain.model.Product
import dev.havlicektomas.discovery_domain.repository.ProductRepo
import kotlinx.coroutines.flow.Flow

class GetProductsUseCase(
    private val repository: ProductRepo
) {

    operator fun invoke(category: String): Flow<List<Product>> {
        return repository.getProducts(category)
    }
}