package dev.havlicektomas.discovery_presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import dev.havlicektomas.core.navigation.Route
import dev.havlicektomas.core.util.UiEvent

@Composable
fun MainScreenScaffoldWithNavRail(
    modifier: Modifier = Modifier,
    shouldShowNavRail: Boolean,
    currentDestination: NavDestination?,
    onBottomBarItemClick: (event: UiEvent.Navigate) -> Unit,
    content: @Composable (contentPadding: PaddingValues) -> Unit
) {
    val navBarItems = listOf(
        BottomBarItem(
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            label = "Home",
            route = Route.HOME
        ),
        BottomBarItem(
            selectedIcon = Icons.Filled.Search,
            unselectedIcon = Icons.Outlined.Search,
            label = "Search",
            route = Route.SEARCH
        ),
        BottomBarItem(
            selectedIcon = Icons.Filled.Favorite,
            unselectedIcon = Icons.Outlined.FavoriteBorder,
            label = "Favourites",
            route = Route.FAVORITES
        )
    )

    Row(
        modifier = Modifier.fillMaxSize()
    ) {
        if (shouldShowNavRail) {
            MainNavigationRail(
                currentDestination = currentDestination,
                items = navBarItems,
                onItemClick = onBottomBarItemClick
            )
        }
        MainScreenScaffold(
            navBarItems = navBarItems,
            shouldShowNavRail = shouldShowNavRail,
            badgeCount = 0,
            currentDestination = currentDestination,
            onBottomBarItemClick = onBottomBarItemClick,
            content = content
        )
    }
}