package dev.havlicektomas.discovery_presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.havlicektomas.coreui.theme.LocalSpacing
import dev.havlicektomas.discovery_domain.model.Product
import dev.havlicektomas.discovery_presentation.components.preview_util.product1
import dev.havlicektomas.discovery_presentation.components.preview_util.product2
import dev.havlicektomas.discovery_presentation.components.preview_util.product3
import dev.havlicektomas.discovery_presentation.components.preview_util.product4
import dev.havlicektomas.discovery_presentation.components.preview_util.product5
import dev.havlicektomas.discovery_presentation.components.preview_util.product6

data class ProductGridState(
    val title: String? = null,
    val products: List<Product> = emptyList()
)

data class ProductGridConfig(
    val onClick: () -> Unit = {},
    val onAddToCartClick: () -> Unit = {}
)

@Composable
fun ProductGrid(
    modifier: Modifier = Modifier,
    state: ProductGridState,
    config: ProductGridConfig
) {
    val spacing = LocalSpacing.current

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp),
        modifier = modifier,
        contentPadding = PaddingValues(spacing.spaceSmall),
        verticalArrangement = Arrangement.spacedBy(spacing.spaceSmall),
        horizontalArrangement = Arrangement.spacedBy(spacing.spaceSmall)
    ) {
        state.title?.let {
            item(
                span = {
                    GridItemSpan(maxLineSpan)
                }
            ) {
                Text(
                    text = it,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(spacing.spaceSmall)
                )
            }
        }
        items(state.products) {product ->
            ProductPortrait(
                state = ProductPortraitState(product = product),
                config = ProductPortraitConfig(
                    onClick = config.onClick,
                    onAddToCartClick = config.onAddToCartClick
                )
            )
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = false, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ProductGridPreview() {
    ProductGrid(
        state = ProductGridState(
            title = "Vegetables",
            products = listOf(product1, product2, product3, product4, product5, product6)
        ),
        config = ProductGridConfig()
    )
}