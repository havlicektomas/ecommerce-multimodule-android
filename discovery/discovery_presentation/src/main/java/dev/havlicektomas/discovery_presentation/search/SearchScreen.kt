package dev.havlicektomas.discovery_presentation.search

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination
import dev.havlicektomas.core.util.UiEvent
import dev.havlicektomas.coreui.theme.EcommercemultimoduleTheme
import dev.havlicektomas.coreui.theme.LocalSpacing
import dev.havlicektomas.discovery_domain.model.ProductCategory
import dev.havlicektomas.discovery_presentation.components.MainScreenScaffoldWithNavRail
import dev.havlicektomas.discovery_presentation.components.ProductCategoryCard
import dev.havlicektomas.discovery_presentation.components.ProductCategoryCardConfig
import dev.havlicektomas.discovery_presentation.components.ProductCategoryCardState
import dev.havlicektomas.discovery_presentation.components.ProductGrid
import dev.havlicektomas.discovery_presentation.components.ProductGridConfig
import dev.havlicektomas.discovery_presentation.components.ProductGridState

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreenView(
    shouldShowNavRail: Boolean,
    state: SearchScreenState,
    currentDestination: NavDestination?,
    onNavigate: (UiEvent.Navigate) -> Unit,
    onEvent: (SearchScreenEvent) -> Unit
) {
    val spacing = LocalSpacing.current

    MainScreenScaffoldWithNavRail(
        modifier = Modifier.fillMaxWidth(),
        shouldShowNavRail = shouldShowNavRail,
        currentDestination = currentDestination,
        onBottomBarItemClick = onNavigate
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = spacing.spaceMedium, bottom = spacing.spaceLarge),
                contentAlignment = Alignment.Center
            ) {
                SearchBar(
                    modifier = Modifier.widthIn(max = 500.dp),
                    query = state.searchInput,
                    onQueryChange = { onEvent(SearchScreenEvent.OnSearchInputChanged(it)) },
                    onSearch = { onEvent(SearchScreenEvent.OnSearchIconClick(state.searchInput)) },
                    active = state.isSearching,
                    onActiveChange = {
                          // TODO: add event to toggle isSearching in state
                    },
                    placeholder = { Text(text = "Search ...") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Rounded.Search,
                            contentDescription = "search icon"
                        )
                    },
                    trailingIcon = {
                        if (state.isSearching) {
                            Icon(
                                imageVector = Icons.Rounded.Close,
                                contentDescription = "search icon",
                                modifier = Modifier.clickable {
                                    onEvent(SearchScreenEvent.OnSearchInputChanged(""))
                                }
                            )
                        }
                    }
                ) {
                    ProductGrid(
                        modifier = Modifier.fillMaxSize(),
                        state = ProductGridState(
                            title = "Found ${state.productResults.size} results",
                            products = state.productResults
                        ),
                        config = ProductGridConfig()
                    )
                }
            }
            if (!state.isSearching) {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 136.dp),
                    contentPadding = PaddingValues(spacing.spaceSmall),
                    verticalArrangement = Arrangement.spacedBy(spacing.spaceSmall),
                    horizontalArrangement = Arrangement.spacedBy(spacing.spaceSmall),
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    items(state.productCategories) {category ->
                        ProductCategoryCard(
                            state = ProductCategoryCardState(name = category.name),
                            config = ProductCategoryCardConfig(
                                onClick = {
                                    //onEvent(SearchScreenEvent.OnCategoryClick(category.name))
                                    onEvent(SearchScreenEvent.OnSearchInputChanged(category.name))
                                }
                            )
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