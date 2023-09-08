package dev.havlicektomas.discovery_presentation.search

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination
import dev.havlicektomas.core.util.UiEvent
import dev.havlicektomas.coreui.theme.EcommercemultimoduleTheme
import dev.havlicektomas.coreui.theme.LocalSpacing
import dev.havlicektomas.discovery_domain.model.ProductCategory
import dev.havlicektomas.discovery_presentation.components.MainNavigationRail
import dev.havlicektomas.discovery_presentation.components.MainScreenScaffold
import dev.havlicektomas.discovery_presentation.components.ProductCategoryCard
import dev.havlicektomas.discovery_presentation.components.ProductCategoryCardConfig
import dev.havlicektomas.discovery_presentation.components.ProductCategoryCardState
import dev.havlicektomas.discovery_presentation.components.SearchTextField
import dev.havlicektomas.discovery_presentation.components.navBarItems

@Composable
fun SearchScreen(
    windowClass: WindowSizeClass,
    currentDestination: NavDestination?,
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: SearchScreenViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    val shouldShowNavRail = windowClass.widthSizeClass != WindowWidthSizeClass.Compact

    SearchScreenView(
        shouldShowNavRail = shouldShowNavRail,
        state = state,
        currentDestination = currentDestination,
        onNavigate = onNavigate,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun SearchScreenView(
    shouldShowNavRail: Boolean,
    state: SearchScreenState,
    currentDestination: NavDestination?,
    onNavigate: (UiEvent.Navigate) -> Unit,
    onEvent: (SearchScreenEvent) -> Unit
) {
    val spacing = LocalSpacing.current

    Row(
        modifier = Modifier.fillMaxSize()
    ) {
        if (shouldShowNavRail) {
            MainNavigationRail(
                currentDestination = currentDestination,
                items = navBarItems,
                onItemClick = onNavigate
            )
        }
        MainScreenScaffold(
            shouldShowNavRail = shouldShowNavRail,
            currentDestination = currentDestination,
            onBottomBarItemClick = onNavigate
        ) { contentPadding ->
            Column(
                modifier = Modifier.padding(contentPadding)
            ) {
                SearchTextField(
                    modifier = Modifier.widthIn(max = 600.dp),
                    text = state.searchInput,
                    onTextChange = { onEvent(SearchScreenEvent.OnSearchInputChanged(it)) },
                    onSearchClick = { onEvent(SearchScreenEvent.OnSearchIconClick(state.searchInput)) }
                )
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 136.dp),
                    contentPadding = PaddingValues(spacing.spaceSmall),
                    verticalArrangement = Arrangement.spacedBy(spacing.spaceSmall),
                    horizontalArrangement = Arrangement.spacedBy(spacing.spaceSmall),
                    modifier = Modifier
                ) {
                    items(state.productCategories) {category ->
                        ProductCategoryCard(
                            state = ProductCategoryCardState(name = category.name),
                            config = ProductCategoryCardConfig(onClick = {})
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = false, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SearchScreenPreview() {
    EcommercemultimoduleTheme {
        SearchScreenView(
            shouldShowNavRail = false,
            state = SearchScreenState(
                searchInput = "",
                productCategories = listOf(
                    ProductCategory("Category 1", "", "category1"),
                    ProductCategory("Category 2", "", "category2"),
                    ProductCategory("Category 3", "", "category3"),
                    ProductCategory("Category 4", "", "category4"),
                    ProductCategory("Category 5", "", "category5"),
                    ProductCategory("Category 6", "", "category6"),
                ),
                productResults = emptyList()
            ),
            currentDestination = null,
            onNavigate = {},
            onEvent = {}
        )
    }
}