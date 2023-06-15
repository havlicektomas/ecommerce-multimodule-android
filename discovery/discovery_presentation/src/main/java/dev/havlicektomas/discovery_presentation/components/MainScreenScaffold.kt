package dev.havlicektomas.discovery_presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.havlicektomas.core.util.UiEvent

@Composable
fun MainScreenScaffold(
    selectedBottomBarItemIndex: Int,
    onBottomBarItemClick: (event: UiEvent.Navigate) -> Unit,
    content: @Composable (contentPadding: PaddingValues) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            MainTopBar(modifier = Modifier.fillMaxWidth())
        },
        bottomBar = {
            MainBottomBar(
                modifier = Modifier.fillMaxWidth(),
                selectedItemIndex = selectedBottomBarItemIndex,
                onItemClick = onBottomBarItemClick
            )
        }
    ) { contentPadding ->
        content(contentPadding)
    }
}