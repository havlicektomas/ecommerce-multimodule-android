package dev.havlicektomas.discovery_domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dev.havlicektomas.discovery_domain.repository.HeroImageRepo
import dev.havlicektomas.discovery_domain.repository.ProductCategoryRepo
import dev.havlicektomas.discovery_domain.repository.ProductRepo
import dev.havlicektomas.discovery_domain.usecase.FetchProductsUseCase
import dev.havlicektomas.discovery_domain.usecase.search.FetchProductCategoriesUseCase
import dev.havlicektomas.discovery_domain.usecase.search.GetProductCategoriesUseCase
import dev.havlicektomas.discovery_domain.usecase.GetProductsUseCase
import dev.havlicektomas.discovery_domain.usecase.search.SearchUseCases
import dev.havlicektomas.discovery_domain.usecase.search.SearchProductsUseCase
import dev.havlicektomas.discovery_domain.usecase.ToggleFavouriteProductUseCase
import dev.havlicektomas.discovery_domain.usecase.favourites.FavouriteUseCases
import dev.havlicektomas.discovery_domain.usecase.home.FetchHeroImagesUseCase
import dev.havlicektomas.discovery_domain.usecase.home.GetHeroImagesUseCase
import dev.havlicektomas.discovery_domain.usecase.home.HomeUseCases

@Module
@InstallIn(ViewModelComponent::class)
object DiscoveryDomainModule {

    @ViewModelScoped
    @Provides
    fun provideSearchUseCases(
        productRepo: ProductRepo,
        productCategoryRepo: ProductCategoryRepo
    ): SearchUseCases {
        return SearchUseCases(
            searchProductsUseCase = SearchProductsUseCase(productRepo),
            getProductsUseCase = GetProductsUseCase(productRepo),
            getProductCategoriesUseCase = GetProductCategoriesUseCase(productCategoryRepo),
            fetchProductCategoriesUseCase = FetchProductCategoriesUseCase(productCategoryRepo)
        )
    }

    @ViewModelScoped
    @Provides
    fun provideFavouriteUseCases(
        productRepo: ProductRepo
    ): FavouriteUseCases {
        return FavouriteUseCases(
            fetchProductsUseCase = FetchProductsUseCase(productRepo),
            getProductsUseCase = GetProductsUseCase(productRepo),
            toggleFavouriteProductUseCase = ToggleFavouriteProductUseCase(productRepo)
        )
    }

    @ViewModelScoped
    @Provides
    fun provideHomeUseCases(
        productRepo: ProductRepo,
        heroImageRepo: HeroImageRepo
    ): HomeUseCases {
        return HomeUseCases(
            fetchProductsUseCase = FetchProductsUseCase(productRepo),
            getProductsUseCase = GetProductsUseCase(productRepo),
            fetchHeroImagesUseCase = FetchHeroImagesUseCase(heroImageRepo),
            getHeroImagesUseCase = GetHeroImagesUseCase(heroImageRepo),
            toggleFavouriteProductUseCase = ToggleFavouriteProductUseCase(productRepo)
        )
    }
}