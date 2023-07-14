package dev.havlicektomas.discovery_presentation.search

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import dev.havlicektomas.discovery_presentation.components.MainScreenScaffold
import dev.havlicektomas.discovery_presentation.components.ProductCategoryCard
import dev.havlicektomas.discovery_presentation.components.ProductCategoryCardConfig
import dev.havlicektomas.discovery_presentation.components.ProductCategoryCardState
import dev.havlicektomas.discovery_presentation.components.SearchTextField

@Composable
fun SearchScreen(
    currentDestination: NavDestination?,
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: SearchScreenViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    SearchScreenView(
        state = state,
        currentDestination = currentDestination,
        onNavigate = onNavigate
    )
}

@Composable
fun SearchScreenView(
    state: SearchScreenState,
    currentDestination: NavDestination?,
    onNavigate: (UiEvent.Navigate) -> Unit
) {
    val spacing = LocalSpacing.current

    MainScreenScaffold(
        currentDestination = currentDestination,
        onBottomBarItemClick = onNavigate
    ) { contentPadding ->
        Column(
            modifier = Modifier.padding(contentPadding)
        ) {
            SearchTextField(
                text = "Search",
                onTextChange = {},
                onSearchClick = {}
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

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = false, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SearchScreenPreview() {
    EcommercemultimoduleTheme {
        SearchScreenView(
            state = SearchScreenState(
                searchInput = "",
                searchPlaceHolder = "Search ...",
                productCategories = listOf(
                    ProductCategory("Category 1", "", "category1"),
                    ProductCategory("Category 2", "", "category2"),
                    ProductCategory("Category 3", "", "category3"),
                    ProductCategory("Category 4", "", "category4"),
                    ProductCategory("Category 5", "", "category5"),
                    ProductCategory("Category 6", "", "category6"),
                )
            ),
            currentDestination = null,
            onNavigate = {}
        )
    }
}