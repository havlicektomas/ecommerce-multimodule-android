package dev.havlicektomas.discovery_presentation.favourite

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import dev.havlicektomas.core.util.UiEvent
import dev.havlicektomas.coreui.theme.EcommercemultimoduleTheme
import dev.havlicektomas.discovery_presentation.components.MainScreenScaffold

@Composable
fun FavouritesScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: FavouritesViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    FavouritesScreenView(
        state = state,
        onNavigate = onNavigate
    )
}

@Composable
fun FavouritesScreenView(
    state: FavouritesScreenState,
    onNavigate: (UiEvent.Navigate) -> Unit
) {
    MainScreenScaffold(
        selectedBottomBarItemIndex = 2,
        onBottomBarItemClick = onNavigate
    ) { contentPadding ->
        LazyColumn(
            modifier = Modifier.padding(contentPadding)
        ) {
            //
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = false, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HomeScreenPreview() {
    EcommercemultimoduleTheme {
        FavouritesScreenView(
            state = FavouritesScreenState(emptyList()),
            onNavigate = {}
        )
    }
}