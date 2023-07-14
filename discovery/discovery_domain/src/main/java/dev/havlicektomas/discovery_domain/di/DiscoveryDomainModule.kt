package dev.havlicektomas.discovery_domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dev.havlicektomas.discovery_domain.repository.FavouriteProductRepository
import dev.havlicektomas.discovery_domain.repository.ProductRepository
import dev.havlicektomas.discovery_domain.usecase.FetchFavouriteProductsUseCase
import dev.havlicektomas.discovery_domain.usecase.FetchProductCategoriesUseCase
import dev.havlicektomas.discovery_domain.usecase.GetFavouriteProductsUseCase
import dev.havlicektomas.discovery_domain.usecase.GetProductByIdUseCase
import dev.havlicektomas.discovery_domain.usecase.GetProductCategoriesUseCase
import dev.havlicektomas.discovery_domain.usecase.GetProductsUseCase
import dev.havlicektomas.discovery_domain.usecase.search.SearchProductUseCases
import dev.havlicektomas.discovery_domain.usecase.SearchProductsUseCase
import dev.havlicektomas.discovery_domain.usecase.ToggleFavouriteProductUseCase
import dev.havlicektomas.discovery_domain.usecase.favourites.FavouriteProductUseCases

@Module
@InstallIn(ViewModelComponent::class)
object DiscoveryDomainModule {

    @ViewModelScoped
    @Provides
    fun provideSearchProductUseCases(
        repository: ProductRepository
    ): SearchProductUseCases {
        return SearchProductUseCases(
            searchProductsUseCase = SearchProductsUseCase(repository),
            getProductsUseCase = GetProductsUseCase(repository),
            getProductByIdUseCase = GetProductByIdUseCase(repository),
            getProductCategoriesUseCase = GetProductCategoriesUseCase(repository),
            fetchProductCategoriesUseCase = FetchProductCategoriesUseCase(repository)
        )
    }

    @ViewModelScoped
    @Provides
    fun provideFavouriteProductUseCases(
        repository: FavouriteProductRepository
    ): FavouriteProductUseCases {
        return FavouriteProductUseCases(
            fetchFavouriteProducts = FetchFavouriteProductsUseCase(repository),
            getFavouriteProductsUseCase = GetFavouriteProductsUseCase(repository),
            toggleFavouriteProductUseCase = ToggleFavouriteProductUseCase(repository)
        )
    }
}