package dev.havlicektomas.discovery_presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.havlicektomas.core.navigation.Route
import dev.havlicektomas.core.util.UiEvent
import dev.havlicektomas.coreui.theme.EcommercemultimoduleTheme
import dev.havlicektomas.coreui.theme.LocalSpacing

@Composable
fun MainBottomBar(
    modifier: Modifier = Modifier,
    selectedItemIndex: Int = 0,
    onItemClick: (event: UiEvent.Navigate) -> Unit
) {
    val spacing = LocalSpacing.current

    Row(
        modifier = modifier
            .padding(vertical = spacing.spaceSmall),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        IconButton(onClick = { onItemClick(UiEvent.Navigate(Route.HOME)) }) {
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = "home icon",
                modifier = Modifier.size(32.dp),
                tint = if (selectedItemIndex == 0) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.onSurface
                }
            )
        }
        IconButton(onClick = { onItemClick(UiEvent.Navigate(Route.SEARCH)) }) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "catalog icon",
                modifier = Modifier.size(32.dp),
                tint = if (selectedItemIndex == 1) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.onSurface
                }
            )
        }
        IconButton(onClick = { onItemClick(UiEvent.Navigate(Route.FAVORITES)) }) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "wishlist icon",
                modifier = Modifier.size(32.dp),
                tint = if (selectedItemIndex == 2) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.onSurface
                }
            )
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = false, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun MainBottomBarPreview() {
    EcommercemultimoduleTheme {
        MainBottomBar(
            modifier = Modifier.fillMaxWidth(),
            selectedItemIndex = 0,
            onItemClick = {}
        )
    }
}