package dev.havlicektomas.onboarding_presentation.account

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
fun AccountScreen(
    viewModel: OnboardingViewModel,
    onNavigateAndPop: (UiEvent.Navigate, String) -> Unit
) {
    AccountScreenView(
        onNavigateAndPop = onNavigateAndPop
    ) {
        viewModel.saveUserSession("anonymous")
    }
}

@Composable
fun AccountScreenView(
    onNavigateAndPop: (UiEvent.Navigate, String) -> Unit,
    onUserSessionCreated: () -> Unit
) {
    val spacing = LocalSpacing.current

    OnboardingScreen(
        isLastScreen = true,
        onFabClick = {
            onUserSessionCreated()
            onNavigateAndPop(UiEvent.Navigate(Route.HOME), Route.ONBOARDING)
        }
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
                text = stringResource(id = R.string.account_title),
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(spacing.spaceSmall))
            Text(
                text = stringResource(id = R.string.account_message),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            Button(
                onClick = {}
            ) {
                Row(
                    modifier = Modifier.width(100.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Text(text = stringResource(id = R.string.account_login_button_label))
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = stringResource(id = R.string.account_login_button_icon_description),
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(spacing.spaceSmall))
            Button(
                onClick = {}
            ) {
                Row(
                    modifier = Modifier.width(100.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Text(text = stringResource(id = R.string.account_register_button_label))
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = stringResource(id = R.string.account_register_button_icon_description),
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = false, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun AccountScreenPreview() {
    EcommercemultimoduleTheme {
        AccountScreenView(
            onNavigateAndPop = { _, _ -> },
            onUserSessionCreated = {}
        )
    }
}