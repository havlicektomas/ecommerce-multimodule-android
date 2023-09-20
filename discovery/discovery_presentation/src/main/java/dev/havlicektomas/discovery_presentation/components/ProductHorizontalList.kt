package dev.havlicektomas.discovery_presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.havlicektomas.coreui.theme.EcommercemultimoduleTheme
import dev.havlicektomas.coreui.theme.LocalSpacing
import dev.havlicektomas.discovery_domain.model.Product
import dev.havlicektomas.discovery_presentation.components.preview_util.product1
import dev.havlicektomas.discovery_presentation.components.preview_util.product2
import dev.havlicektomas.discovery_presentation.components.preview_util.product3

data class ProductHorizontalListState(
    val title: String = "List title",
    val products: List<Product> = emptyList()
)

data class ProductHorizontalListConfig(
    val onProductClick: (product: Product) -> Unit = {},
    val onAddToCartClick: () -> Unit = {}
)

@Composable
fun ProductHorizontalList(
    modifier: Modifier = Modifier,
    state: ProductHorizontalListState,
    config: ProductHorizontalListConfig
) {
    val spacing = LocalSpacing.current

    Column(
        modifier = modifier
    ) {
        Text(
            text = state.title,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(spacing.spaceSmall)
        )
        LazyRow(
            contentPadding = PaddingValues(horizontal = spacing.spaceSmall),
            horizontalArrangement = Arrangement.spacedBy(spacing.spaceSmall)
        ) {
            items(state.products) {
                ProductPortrait(
                    modifier = Modifier.width(144.dp),
                    state = ProductPortraitState(product = it),
                    config = ProductPortraitConfig(
                        onClick = { config.onProductClick(it) }
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = false, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ProductHorizontalListPreview() {
    EcommercemultimoduleTheme {
        ProductHorizontalList(
            state = ProductHorizontalListState(
                products = listOf(product1, product2, product3)
            ),
            config = ProductHorizontalListConfig()
        )
    }
}