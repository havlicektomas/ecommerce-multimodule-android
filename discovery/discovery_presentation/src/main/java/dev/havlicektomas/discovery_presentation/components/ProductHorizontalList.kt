package dev.havlicektomas.discovery_presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.havlicektomas.coreui.theme.EcommercemultimoduleTheme
import dev.havlicektomas.coreui.theme.LocalSpacing
import dev.havlicektomas.discovery_domain.model.Product

data class ProductHorizontalListState(
    val title: String = "List title",
    val products: List<Product> = emptyList()
)

data class ProductHorizontalListConfig(
    val onClick: () -> Unit = {},
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
                    state = ProductPortraitState(product = it),
                    config = ProductPortraitConfig(
                        onClick = config.onClick
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
            state = ProductHorizontalListState(),
            config = ProductHorizontalListConfig()
        )
    }
}