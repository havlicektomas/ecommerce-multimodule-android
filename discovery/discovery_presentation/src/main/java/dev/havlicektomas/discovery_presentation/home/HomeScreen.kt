package dev.havlicektomas.discovery_presentation.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import dev.havlicektomas.core.util.UiEvent
import dev.havlicektomas.coreui.theme.EcommercemultimoduleTheme
import dev.havlicektomas.discovery_domain.model.Product
import dev.havlicektomas.discovery_presentation.components.HeroImageSlider
import dev.havlicektomas.discovery_presentation.components.MainScreenScaffold
import dev.havlicektomas.discovery_presentation.components.ProductHorizontalList
import dev.havlicektomas.discovery_presentation.components.ProductHorizontalListConfig
import dev.havlicektomas.discovery_presentation.components.ProductHorizontalListState

@Composable
fun HomeScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    HomeScreenView(
        state = state,
        onNavigate = onNavigate
    )
}

@Composable
fun HomeScreenView(
    state: HomeScreenState,
    onNavigate: (UiEvent.Navigate) -> Unit
) {
    MainScreenScaffold(
        selectedBottomBarItemIndex = 0,
        onBottomBarItemClick = onNavigate
    ) { contentPadding ->
        LazyColumn(
            modifier = Modifier.padding(contentPadding)
        ) {
            item {
                HeroImageSlider(modifier = Modifier.fillMaxWidth())
            }
            items(state.productCategories.keys.toList()) { key ->
                state.productCategories[key]?.let {products ->
                    ProductHorizontalList(
                        state = ProductHorizontalListState(
                            title = key,
                            products = products
                        ),
                        config = ProductHorizontalListConfig()
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = false, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun WelcomeScreenPreview() {
    EcommercemultimoduleTheme {
        HomeScreenView(
            state = HomeScreenState(
                heroImageUrls = emptyList(),
                productCategories = mapOf(
                    "featured" to listOf(fakeFeaturedProductDto(), fakeFeaturedProductDto(), fakeFeaturedProductDto()),
                    "onsale" to listOf(fakeOnsaleProductDto(), fakeOnsaleProductDto(), fakeOnsaleProductDto()),
                    "foryou" to listOf(fakeForyouProductDto(), fakeForyouProductDto(), fakeForyouProductDto())
                )
            ),
            onNavigate = {}
        )
    }
}

private fun fakeFeaturedProductDto() = Product(
    id = "123",
    name = "Product 1",
    brand = "SomeBrand",
    description = "Some description goes here",
    price = 9.99,
    category = "Category1",
    tag = "featured"
)

private fun fakeOnsaleProductDto() = Product(
    id = "123",
    name = "Product 1",
    brand = "SomeBrand",
    description = "Some description goes here",
    price = 9.99,
    category = "Category1",
    tag = "onsale"
)

private fun fakeForyouProductDto() = Product(
    id = "123",
    name = "Product 1",
    brand = "SomeBrand",
    description = "Some description goes here",
    price = 9.99,
    category = "Category1",
    tag = "foryou"
)