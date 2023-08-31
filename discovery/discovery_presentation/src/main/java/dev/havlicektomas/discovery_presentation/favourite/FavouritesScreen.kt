package dev.havlicektomas.discovery_presentation.favourite

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination
import dev.havlicektomas.core.util.UiEvent
import dev.havlicektomas.coreui.theme.EcommercemultimoduleTheme
import dev.havlicektomas.discovery_presentation.components.MainNavigationRail
import dev.havlicektomas.discovery_presentation.components.MainScreenScaffold
import dev.havlicektomas.discovery_presentation.components.navBarItems

@Composable
fun FavouritesScreen(
    windowClass: WindowSizeClass,
    currentDestination: NavDestination?,
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: FavouritesViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    val shouldShowNavRail = windowClass.widthSizeClass != WindowWidthSizeClass.Compact

    FavouritesScreenView(
        shouldShowNavRail = shouldShowNavRail,
        state = state,
        currentDestination = currentDestination,
        onNavigate = onNavigate
    )
}

@Composable
fun FavouritesScreenView(
    shouldShowNavRail: Boolean,
    state: FavouritesScreenState,
    currentDestination: NavDestination?,
    onNavigate: (UiEvent.Navigate) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxSize()
    ) {
        if (shouldShowNavRail) {
            MainNavigationRail(
                currentDestination = currentDestination,
                items = navBarItems,
                onItemClick = onNavigate
            )
        }
        MainScreenScaffold(
            shouldShowNavRail = shouldShowNavRail,
            currentDestination = currentDestination,
            onBottomBarItemClick = onNavigate
        ) { contentPadding ->
            LazyColumn(
                modifier = Modifier.padding(contentPadding)
            ) {
                //
            }
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = false, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HomeScreenPreview() {
    EcommercemultimoduleTheme {
        FavouritesScreenView(
            shouldShowNavRail = false,
            state = FavouritesScreenState(emptyList()),
            currentDestination = null,
            onNavigate = {}
        )
    }
}