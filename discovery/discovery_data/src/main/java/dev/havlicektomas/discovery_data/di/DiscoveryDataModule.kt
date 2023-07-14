package dev.havlicektomas.discovery_data.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.havlicektomas.discovery_data.local.DiscoveryDatabase
import dev.havlicektomas.discovery_data.remote.ProductApi
import dev.havlicektomas.discovery_data.repository.FakeProductRepository
import dev.havlicektomas.discovery_data.repository.FavouriteProductRepositoryImpl
import dev.havlicektomas.discovery_domain.repository.FavouriteProductRepository
import dev.havlicektomas.discovery_domain.repository.ProductRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DiscoveryDataModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideProductApi(client: OkHttpClient): ProductApi {
        return Retrofit.Builder()
            .baseUrl(ProductApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideDiscoveryDatabase(app: Application): DiscoveryDatabase {
        return Room.databaseBuilder(
            app,
            DiscoveryDatabase::class.java,
            "discovery_db"
        ).build()
    }

//    @Provides
//    @Singleton
//    fun provideProductRepository(
//        api: ProductApi,
//        db: DiscoveryDatabase
//    ): ProductRepository {
//        return ProductRepositoryImpl(
//            productDao = db.dao,
//            productApi = api
//        )
//    }

    @Provides
    @Singleton
    fun provideProductRepository(
        api: ProductApi,
        db: DiscoveryDatabase
    ): ProductRepository {
        return FakeProductRepository(
            productDao = db.productDao
        )
    }

    @Provides
    @Singleton
    fun provideFavouriteProductRepository(
        api: ProductApi,
        db: DiscoveryDatabase
    ): FavouriteProductRepository {
        return FavouriteProductRepositoryImpl(
            productApi = api,
            favouriteProductDao = db.favouriteProductDao
        )
    }
}