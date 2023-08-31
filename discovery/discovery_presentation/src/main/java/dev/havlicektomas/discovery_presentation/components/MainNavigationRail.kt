package dev.havlicektomas.discovery_presentation.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import dev.havlicektomas.core.util.UiEvent

@Composable
fun MainNavigationRail(
    modifier: Modifier = Modifier,
    currentDestination: NavDestination?,
    items: List<BottomBarItem>,
    onItemClick: (event: UiEvent.Navigate) -> Unit = {}
) {
    NavigationRail(
        modifier = modifier
    ) {
        items.forEach { item ->
            val isSelected = currentDestination?.hierarchy?.any { it.route == item.route } == true

            NavigationRailItem(
                selected = isSelected,
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
                onClick = {
                    onItemClick(UiEvent.Navigate(item.route))
                }
            )
        }
    }
}