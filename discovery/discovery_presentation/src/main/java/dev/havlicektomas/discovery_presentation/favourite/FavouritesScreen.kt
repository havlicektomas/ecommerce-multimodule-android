package dev.havlicektomas.discovery_presentation.favourite

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
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
import dev.havlicektomas.discovery_presentation.components.MainScreenScaffoldWithNavRail
import dev.havlicektomas.discovery_presentation.components.ProductGrid
import dev.havlicektomas.discovery_presentation.components.ProductGridConfig
import dev.havlicektomas.discovery_presentation.components.ProductGridState
import dev.havlicektomas.discovery_presentation.components.preview_util.product1
import dev.havlicektomas.discovery_presentation.components.preview_util.product2
import dev.havlicektomas.discovery_presentation.components.preview_util.product3

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
    MainScreenScaffoldWithNavRail(
        shouldShowNavRail = shouldShowNavRail,
        currentDestination = currentDestination,
        onBottomBarItemClick = onNavigate
    ) { contentPadding ->
        ProductGrid(
            modifier = Modifier.padding(contentPadding),
            state = ProductGridState(
                title = "Favourites",
                products = state.favouriteProducts
            ),
            config = ProductGridConfig()
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = false, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HomeScreenPreview() {
    EcommercemultimoduleTheme {
        FavouritesScreenView(
            shouldShowNavRail = false,
            state = FavouritesScreenState(
                favouriteProducts = listOf(product1, product2, product3)
            ),
            currentDestination = null,
            onNavigate = {}
        )
    }
}