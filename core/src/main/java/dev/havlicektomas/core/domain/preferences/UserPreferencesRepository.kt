package dev.havlicektomas.core.domain.preferences

import kotlinx.coroutines.flow.Flow

interface UserPreferencesRepository {
    suspend fun savePolicyResponse(policyAccepted: Boolean)
    fun loadPolicyResponse(): Flow<Boolean>

    suspend fun saveUserSession(session: String)
    fun loadUserSession(): Flow<String>
}