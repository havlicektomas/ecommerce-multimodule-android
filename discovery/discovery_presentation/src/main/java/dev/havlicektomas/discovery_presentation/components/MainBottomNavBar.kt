package dev.havlicektomas.discovery_presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
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
    val icon: ImageVector,
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
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        text = item.label
                    )
                },
                alwaysShowLabel = true,
                selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
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
        )
    }
}
