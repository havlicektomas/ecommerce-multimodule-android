package dev.havlicektomas.ecommerce.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import dev.havlicektomas.core.navigation.Route
import dev.havlicektomas.core.util.UiEvent
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@Composable
fun SplashScreen(
    viewModel: SplashScreenViewModel = hiltViewModel(),
    onNavigateAndPop: (UiEvent.Navigate, String) -> Unit
) {
    val onboardingCompleted by viewModel.onboardingCompleted.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = MaterialTheme.colorScheme.onBackground)
        }
    }

    LaunchedEffect(key1 = true) {
        // TODO: for testing only
        delay(1.seconds)

        if (onboardingCompleted) {
            onNavigateAndPop(UiEvent.Navigate(Route.HOME), Route.SPLASH)
        } else {
            onNavigateAndPop(UiEvent.Navigate(Route.ONBOARDING), Route.SPLASH)
        }
    }
}