package dev.havlicektomas.core.data.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import dev.havlicektomas.core.domain.preferences.UserPreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DefaultUserPreferencesRepository  @Inject constructor(
    private val userDataStorePreferences: DataStore<Preferences>
) : UserPreferencesRepository {

    override suspend fun savePolicyResponse(policyAccepted: Boolean) {
        userDataStorePreferences.edit { preferences ->
            preferences[POLICY_ACCEPTED] = policyAccepted
        }
    }

    override fun loadPolicyResponse(): Flow<Boolean> {
        return userDataStorePreferences.data.map { preferences ->
            preferences[POLICY_ACCEPTED] ?: false
        }
    }

    override suspend fun saveUserSession(session: String) {
        userDataStorePreferences.edit { preferences ->
            preferences[USER_SESSION] = session
        }
    }

    override fun loadUserSession(): Flow<String> {
        return userDataStorePreferences.data.map { preferences ->
            preferences[USER_SESSION] ?: ""
        }
    }

    companion object {
        val POLICY_ACCEPTED = booleanPreferencesKey("policy_accepted")
        val USER_SESSION = stringPreferencesKey("user_session")
    }
}