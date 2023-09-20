package dev.havlicektomas.discovery_presentation.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination
import dev.havlicektomas.core.util.UiEvent
import dev.havlicektomas.coreui.theme.EcommercemultimoduleTheme
import dev.havlicektomas.discovery_domain.model.HeroImage
import dev.havlicektomas.discovery_domain.model.Product
import dev.havlicektomas.discovery_presentation.components.HeroImageSlider
import dev.havlicektomas.discovery_presentation.components.MainScreenScaffoldWithNavRail
import dev.havlicektomas.discovery_presentation.components.ProductDetailConfig
import dev.havlicektomas.discovery_presentation.components.ProductDetailSheet
import dev.havlicektomas.discovery_presentation.components.ProductDetailState
import dev.havlicektomas.discovery_presentation.components.ProductHorizontalList
import dev.havlicektomas.discovery_presentation.components.ProductHorizontalListConfig
import dev.havlicektomas.discovery_presentation.components.ProductHorizontalListState
import dev.havlicektomas.discovery_presentation.components.preview_util.loremIpsumText
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    windowClass: WindowSizeClass,
    currentDestination: NavDestination?,
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val shouldShowNavRail = windowClass.widthSizeClass != WindowWidthSizeClass.Compact

    HomeScreenView(
        shouldShowNavRail = shouldShowNavRail,
        state = state,
        currentDestination = currentDestination,
        selectedProduct = viewModel.selectedProduct,
        onProductDetailShow = { product -> viewModel.onEvent(HomeScreenEvent.OnProductDetailShow(product)) },
        onProductDetailHide = { viewModel.onEvent(HomeScreenEvent.OnProductDetailHide) },
        onNavigate = onNavigate
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenView(
    shouldShowNavRail: Boolean,
    state: HomeScreenState,
    currentDestination: NavDestination?,
    selectedProduct: Product?,
    onProductDetailShow: (product: Product) -> Unit,
    onProductDetailHide: () -> Unit,
    onNavigate: (UiEvent.Navigate) -> Unit
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    var isSheetVisible by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    MainScreenScaffoldWithNavRail(
        shouldShowNavRail = shouldShowNavRail,
        currentDestination = currentDestination,
        onBottomBarItemClick = onNavigate
    ) { contentPadding ->
        LazyColumn(
            contentPadding = contentPadding
        ) {
            item {
                HeroImageSlider(
                    modifier = Modifier.fillMaxWidth(),
                    images = state.heroImages
                )
            }
            items(state.productCategories.keys.toList()) { key ->
                state.productCategories[key]?.let { products ->
                    ProductHorizontalList(
                        modifier = Modifier.fillMaxWidth(),
                        state = ProductHorizontalListState(
                            title = key,
                            products = products
                        ),
                        config = ProductHorizontalListConfig(
                            onProductClick = { product ->
                                scope.launch {
                                    onProductDetailShow(product)
                                    isSheetVisible = true
                                }
                            }
                        )
                    )
                }
            }
            item {
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
        if (isSheetVisible) {
            selectedProduct?.let {
                ProductDetailSheet(
                    modifier = Modifier.padding(contentPadding),
                    state = ProductDetailState(
                        product = it.copy(description = loremIpsumText)
                    ),
                    config = ProductDetailConfig(),
                    sheetState = sheetState,
                    onDismissRequest = {
                        scope.launch {
                            onProductDetailHide()
                            isSheetVisible = false
                        }
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = false, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HomeScreenPreview() {
    EcommercemultimoduleTheme {
        HomeScreenView(
            shouldShowNavRail = false,
            state = HomeScreenState(
                heroImages = listOf(
                    HeroImage("1", "", ""),
                    HeroImage("2", "", ""),
                    HeroImage("3", "", "")
                ),
                productCategories = mapOf(
                    "featured" to listOf(previewFeaturedProductDto(), previewFeaturedProductDto(), previewFeaturedProductDto()),
                    "onsale" to listOf(previewOnsaleProductDto(), previewOnsaleProductDto(), previewOnsaleProductDto()),
                    "foryou" to listOf(previewForyouProductDto(), previewForyouProductDto(), previewForyouProductDto())
                )
            ),
            currentDestination = null,
            selectedProduct = null,
            onProductDetailShow = {},
            onProductDetailHide = {},
            onNavigate = {}
        )
    }
}

private fun previewFeaturedProductDto() = Product(
    id = "123",
    name = "Carrot",
    brand = "SomeBrand",
    description = "Some description goes here",
    price = 9.99,
    category = "Category1",
    tag = "featured",
    imageUrl = ""
)

private fun previewOnsaleProductDto() = Product(
    id = "123",
    name = "Potatoes",
    brand = "SomeBrand",
    description = "Some description goes here",
    price = 9.99,
    category = "Category1",
    tag = "onsale",
    imageUrl = ""
)

private fun previewForyouProductDto() = Product(
    id = "123",
    name = "Pepper",
    brand = "SomeBrand",
    description = "Some description goes here",
    price = 9.99,
    category = "Category1",
    tag = "foryou",
    imageUrl = ""
)