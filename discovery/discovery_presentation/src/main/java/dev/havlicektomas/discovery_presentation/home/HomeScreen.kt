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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination
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
    currentDestination: NavDestination?,
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    HomeScreenView(
        state = state,
        currentDestination = currentDestination,
        onNavigate = onNavigate
    )
}

@Composable
fun HomeScreenView(
    state: HomeScreenState,
    currentDestination: NavDestination?,
    onNavigate: (UiEvent.Navigate) -> Unit
) {
    MainScreenScaffold(
        currentDestination = currentDestination,
        onBottomBarItemClick = onNavigate
    ) { contentPadding ->
        LazyColumn(
            modifier = Modifier.padding(bottom = 32.dp),
            contentPadding = contentPadding
        ) {
            item {
                HeroImageSlider(
                    modifier = Modifier.fillMaxWidth(),
                    images = listOf(
                        "https://firebasestorage.googleapis.com/v0/b/ecommerce-multimodule.appspot.com/o/cabbage.webp?alt=media&token=de5c45db-a017-43b1-aae8-9dd0bf9f1085",
                        "https://firebasestorage.googleapis.com/v0/b/ecommerce-multimodule.appspot.com/o/cabbage.webp?alt=media&token=de5c45db-a017-43b1-aae8-9dd0bf9f1085",
                        "https://firebasestorage.googleapis.com/v0/b/ecommerce-multimodule.appspot.com/o/cabbage.webp?alt=media&token=de5c45db-a017-43b1-aae8-9dd0bf9f1085"
                    )
                )
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
fun HomeScreenPreview() {
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
            currentDestination = null,
            onNavigate = {}
        )
    }
}

private fun fakeFeaturedProductDto() = Product(
    id = "123",
    name = "Carrot",
    brand = "SomeBrand",
    description = "Some description goes here",
    price = 9.99,
    category = "Category1",
    tag = "featured",
    imageUrl = ""
)

private fun fakeOnsaleProductDto() = Product(
    id = "123",
    name = "Potatoes",
    brand = "SomeBrand",
    description = "Some description goes here",
    price = 9.99,
    category = "Category1",
    tag = "onsale",
    imageUrl = ""
)

private fun fakeForyouProductDto() = Product(
    id = "123",
    name = "Pepper",
    brand = "SomeBrand",
    description = "Some description goes here",
    price = 9.99,
    category = "Category1",
    tag = "foryou",
    imageUrl = ""
)