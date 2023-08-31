package dev.havlicektomas.discovery_presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.navigation.NavDestination
import dev.havlicektomas.core.navigation.Route
import dev.havlicektomas.core.util.UiEvent

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenScaffold(
    shouldShowNavRail: Boolean = false,
    badgeCount: Int = 0,
    currentDestination: NavDestination?,
    onBottomBarItemClick: (event: UiEvent.Navigate) -> Unit,
    content: @Composable (contentPadding: PaddingValues) -> Unit
) {
    val portraitScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val landscapeScrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val scrollBehavior = if (shouldShowNavRail) landscapeScrollBehavior else portraitScrollBehavior

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            MainTopAppBar(
                badgeCount = badgeCount,
                scrollBehavior = scrollBehavior
            )
        },
        bottomBar = {
            if (!shouldShowNavRail) {
                MainBottomNavBar(
                    currentDestination = currentDestination,
                    items = navBarItems,
                    onItemClick = onBottomBarItemClick
                )
            }
        }
    ) { contentPadding ->
        content(contentPadding)
    }
}