package dev.havlicektomas.onboarding_presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.havlicektomas.core.domain.preferences.UserPreferencesRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository
): ViewModel() {

    val onboardingCompleted = combine(
        userPreferencesRepository.loadPolicyResponse(),
        userPreferencesRepository.loadUserSession()
    ) { policyAccepted, userSession ->
        return@combine policyAccepted && userSession.isNotEmpty()
    }
        .stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = false
    )

    val policyAcceptedState = userPreferencesRepository.loadPolicyResponse()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = false
        )

    val userSessionState = userPreferencesRepository.loadUserSession()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = ""
        )

    fun savePolicyAccepted(accepted: Boolean) {
        viewModelScope.launch {
            userPreferencesRepository.savePolicyResponse(accepted)
        }
    }

    fun saveUserSession(session: String) {
        viewModelScope.launch {
            userPreferencesRepository.saveUserSession(session)
        }
    }
}