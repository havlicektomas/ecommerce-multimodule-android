package dev.havlicektomas.onboarding_presentation.welcome

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.havlicektomas.core.navigation.Route
import dev.havlicektomas.core.util.UiEvent
import dev.havlicektomas.coreui.theme.EcommercemultimoduleTheme
import dev.havlicektomas.coreui.theme.LocalSpacing
import dev.havlicektomas.ecommerce.R
import dev.havlicektomas.onboarding_presentation.OnboardingViewModel
import dev.havlicektomas.onboarding_presentation.components.OnboardingScreen

@Composable
fun WelcomeScreen(
    viewModel: OnboardingViewModel,
    onNavigate: (UiEvent.Navigate) -> Unit,
    onNavigateAndPop: (UiEvent.Navigate, String) -> Unit
) {
    val onboardingCompleted by viewModel.onboardingCompleted.collectAsState()

    if (onboardingCompleted) {
        LaunchedEffect(key1 = true) {
            onNavigateAndPop(UiEvent.Navigate(Route.HOME), Route.WELCOME)
        }
    } else {
        WelcomeScreenView(
            onNavigate = onNavigate
        )
    }
}

@Composable
fun WelcomeScreenView(
    onNavigate: (UiEvent.Navigate) -> Unit
) {
    val spacing = LocalSpacing.current

    OnboardingScreen(
        onFabClick = { onNavigate(UiEvent.Navigate(Route.POLICIES)) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = spacing.spaceLarge),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = stringResource(id = R.string.welcome_icon_description),
                modifier = Modifier.size(56.dp)
            )
            Spacer(modifier = Modifier.height(spacing.spaceLarge))
            Text(
                text = stringResource(id = R.string.welcome_title),
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(spacing.spaceSmall))
            Text(
                text = stringResource(id = R.string.welcome_message),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = false, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun WelcomeScreenPreview() {
    EcommercemultimoduleTheme {
        WelcomeScreenView(
            onNavigate = {}
        )
    }
}