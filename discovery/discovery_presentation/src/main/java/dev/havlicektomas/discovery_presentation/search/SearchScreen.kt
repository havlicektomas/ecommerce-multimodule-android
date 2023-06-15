package dev.havlicektomas.discovery_presentation.search

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import dev.havlicektomas.core.util.UiEvent
import dev.havlicektomas.coreui.theme.EcommercemultimoduleTheme
import dev.havlicektomas.discovery_presentation.components.MainScreenScaffold

@Composable
fun SearchScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: SearchScreenViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    SearchScreenView(
        state = state,
        onNavigate = onNavigate
    )
}

@Composable
fun SearchScreenView(
    state: SearchScreenState,
    onNavigate: (UiEvent.Navigate) -> Unit
) {
    MainScreenScaffold(
        selectedBottomBarItemIndex = 1,
        onBottomBarItemClick = onNavigate
    ) { contentPadding ->
        //
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = false, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SearchScreenPreview() {
    EcommercemultimoduleTheme {
        SearchScreenView(
            state = SearchScreenState(
                searchInput = "",
                searchPlaceHolder = "Search ...",
                productCategories = emptyList()
            ),
            onNavigate = {}
        )
    }
}