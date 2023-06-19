package dev.havlicektomas.discovery_presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.havlicektomas.core.navigation.Route
import dev.havlicektomas.core.util.UiEvent
import dev.havlicektomas.coreui.theme.EcommercemultimoduleTheme

@Composable
fun MainBottomNavBar(
    modifier: Modifier = Modifier,
    selectedItemIndex: Int = 0,
    onItemClick: (event: UiEvent.Navigate) -> Unit
) {
    BottomAppBar(
        modifier = modifier
    ) {
        NavigationBar {
            NavigationBarItem(
                selected = selectedItemIndex == 0,
                onClick = { onItemClick(UiEvent.Navigate(Route.HOME)) },
                icon = { 
                    Icon(imageVector = Icons.Default.Home, contentDescription = "home")
                },
                label = {
                    Text("Home")
                },
                alwaysShowLabel = true
            )
            NavigationBarItem(
                selected = selectedItemIndex == 1,
                onClick = { onItemClick(UiEvent.Navigate(Route.SEARCH)) },
                icon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "search")
                },
                label = {
                    Text("Search")
                },
                alwaysShowLabel = true
            )
            NavigationBarItem(
                selected = selectedItemIndex == 2,
                onClick = { onItemClick(UiEvent.Navigate(Route.FAVORITES)) },
                icon = {
                    Icon(imageVector = Icons.Default.Star, contentDescription = "favourite")
                },
                label = {
                    Text("Favourites")
                },
                alwaysShowLabel = true
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainBottomNavBarPreview() {
    EcommercemultimoduleTheme {
        MainBottomNavBar(
            selectedItemIndex = 0,
            onItemClick = {}
        )
    }
}
