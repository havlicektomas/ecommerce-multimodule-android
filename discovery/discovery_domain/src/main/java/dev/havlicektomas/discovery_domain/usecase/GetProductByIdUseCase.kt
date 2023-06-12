package dev.havlicektomas.discovery_domain.usecase

import dev.havlicektomas.discovery_domain.model.Product
import dev.havlicektomas.discovery_domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class GetProductByIdUseCase(
    private val repository: ProductRepository
) {

    operator fun invoke(
        productId: String
    ): Flow<Product> {
        return repository.getProductById(productId)
    }
}