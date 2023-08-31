package dev.havlicektomas.discovery_presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import dev.havlicektomas.core.navigation.Route
import dev.havlicektomas.core.util.UiEvent
import dev.havlicektomas.coreui.theme.EcommercemultimoduleTheme

data class BottomBarItem(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val label: String,
    val route: String,
)

@Composable
fun MainBottomNavBar(
    modifier: Modifier = Modifier,
    currentDestination: NavDestination?,
    items: List<BottomBarItem>,
    onItemClick: (event: UiEvent.Navigate) -> Unit = {}
) {
    BottomAppBar(
        modifier = modifier
    ) {
        items.forEach { item ->

            val isSelected = currentDestination?.hierarchy?.any { it.route == item.route } == true

            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = if (isSelected) item.selectedIcon else item.unselectedIcon,
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        text = item.label
                    )
                },
                alwaysShowLabel = true,
                selected = isSelected,
                onClick = {
                    onItemClick(UiEvent.Navigate(item.route))
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainBottomNavBarPreview() {
    EcommercemultimoduleTheme {
        MainBottomNavBar(
            currentDestination = null,
            items = listOf(
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
        )
    }
}
