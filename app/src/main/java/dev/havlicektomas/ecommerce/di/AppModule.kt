package dev.havlicektomas.ecommerce.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.havlicektomas.core.data.preferences.DefaultUserPreferencesRepository
import dev.havlicektomas.core.domain.preferences.UserPreferencesRepository
import javax.inject.Singleton

val Context.userDataStore: DataStore<Preferences> by preferencesDataStore(
    name = "dev.havlicektomas.ecommerce.user_preferences"
)

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    // binds instance of MyUserPreferencesRepository
    @Binds
    @Singleton
    abstract fun bindUserPreferencesRepository(
        defaultUserPreferencesRepository: DefaultUserPreferencesRepository
    ): UserPreferencesRepository

    companion object {

        // provides instance of DataStore
        @Provides
        @Singleton
        fun provideUserDataStorePreferences(
            @ApplicationContext applicationContext: Context
        ): DataStore<Preferences> {
            return applicationContext.userDataStore
        }
    }
}