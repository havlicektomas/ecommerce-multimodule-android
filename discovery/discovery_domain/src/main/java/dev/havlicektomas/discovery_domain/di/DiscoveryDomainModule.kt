package dev.havlicektomas.discovery_domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dev.havlicektomas.discovery_domain.repository.ProductRepository
import dev.havlicektomas.discovery_domain.usecase.FetchProductCategoriesUseCase
import dev.havlicektomas.discovery_domain.usecase.GetProductByIdUseCase
import dev.havlicektomas.discovery_domain.usecase.GetProductCategoriesUseCase
import dev.havlicektomas.discovery_domain.usecase.GetProductsUseCase
import dev.havlicektomas.discovery_domain.usecase.ProductUseCases
import dev.havlicektomas.discovery_domain.usecase.SearchProductsUseCase

@Module
@InstallIn(ViewModelComponent::class)
object DiscoveryDomainModule {

    @ViewModelScoped
    @Provides
    fun provideProductUseCases(
        repository: ProductRepository
    ): ProductUseCases {
        return ProductUseCases(
            searchProductsUseCase = SearchProductsUseCase(repository),
            getProductsUseCase = GetProductsUseCase(repository),
            getProductByIdUseCase = GetProductByIdUseCase(repository),
            getProductCategoriesUseCase = GetProductCategoriesUseCase(repository),
            fetchProductCategoriesUseCase = FetchProductCategoriesUseCase(repository)
        )
    }
}