package dev.havlicektomas.discovery_presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.navigation.NavDestination
import dev.havlicektomas.core.navigation.Route
import dev.havlicektomas.core.util.UiEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenScaffold(
    currentDestination: NavDestination?,
    onBottomBarItemClick: (event: UiEvent.Navigate) -> Unit,
    content: @Composable (contentPadding: PaddingValues) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    val bottomBarItems = listOf(
        BottomBarItem(
            icon = Icons.Default.Home,
            label = "Home",
            route = Route.HOME
        ),
        BottomBarItem(
            icon = Icons.Default.Search,
            label = "Search",
            route = Route.SEARCH
        ),
        BottomBarItem(
            icon = Icons.Default.Star,
            label = "Favourites",
            route = Route.FAVORITES
        )
    )

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            MainTopAppBar(
                scrollBehavior = scrollBehavior
            )
        },
        bottomBar = {
            MainBottomNavBar(
                currentDestination = currentDestination,
                items = bottomBarItems,
                onItemClick = onBottomBarItemClick
            )
        }
    ) { contentPadding ->
        content(contentPadding)
    }
}