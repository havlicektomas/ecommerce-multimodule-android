package dev.havlicektomas.discovery_data.di

import android.app.Application
import androidx.room.Room
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.havlicektomas.discovery_data.local.DiscoveryDatabase
import dev.havlicektomas.discovery_data.remote.HeroImageApi
import dev.havlicektomas.discovery_data.remote.ProductApi
import dev.havlicektomas.discovery_data.remote.ProductCategoryApi
import dev.havlicektomas.discovery_data.repository.HeroImageRepoImpl
import dev.havlicektomas.discovery_data.repository.ProductCategoryRepoImpl
import dev.havlicektomas.discovery_data.repository.ProductRepoImpl
import dev.havlicektomas.discovery_domain.repository.HeroImageRepo
import dev.havlicektomas.discovery_domain.repository.ProductCategoryRepo
import dev.havlicektomas.discovery_domain.repository.ProductRepo
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

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        return Retrofit.Builder()
            .baseUrl(ProductApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideProductCategoryApi(client: OkHttpClient): ProductCategoryApi {

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        return Retrofit.Builder()
            .baseUrl(ProductCategoryApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideHeroImageApi(client: OkHttpClient): HeroImageApi {

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        return Retrofit.Builder()
            .baseUrl(HeroImageApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
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

    @Provides
    @Singleton
    fun provideProductRepository(
        api: ProductApi,
        db: DiscoveryDatabase
    ): ProductRepo {
        return ProductRepoImpl(
            productDao = db.productDao,
            productApi = api
        )
    }

    @Provides
    @Singleton
    fun provideProductCategoryRepository(
        api: ProductCategoryApi,
        db: DiscoveryDatabase
    ): ProductCategoryRepo {
        return ProductCategoryRepoImpl(
            productCategoryDao = db.productCategoryDao,
            productCategoryApi = api
        )
    }

    @Provides
    @Singleton
    fun provideHeroImageRepository(
        api: HeroImageApi,
        db: DiscoveryDatabase
    ): HeroImageRepo {
        return HeroImageRepoImpl(
            heroImageDao = db.heroImageDao,
            heroImageApi = api
        )
    }
}