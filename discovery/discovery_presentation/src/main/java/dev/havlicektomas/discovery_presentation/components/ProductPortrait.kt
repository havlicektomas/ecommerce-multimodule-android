package dev.havlicektomas.discovery_presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.havlicektomas.coreui.theme.EcommercemultimoduleTheme
import dev.havlicektomas.coreui.theme.LocalSpacing
import dev.havlicektomas.discovery_domain.model.Product

data class ProductPortraitState(
    val product: Product
)

data class ProductPortraitConfig(
    val onClick: () -> Unit = {},
    val onAddToCartClick: () -> Unit = {}
)

@Composable
fun ProductPortrait(
    modifier: Modifier = Modifier,
    state: ProductPortraitState,
    config: ProductPortraitConfig
) {
    val spacing = LocalSpacing.current

    Column(
        modifier = modifier.width(144.dp)
    ) {
        // TODO: product image placeholder
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(128.dp)
                .background(color = Color.LightGray),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = "product image",
                modifier = Modifier.size(40.dp),
                tint = MaterialTheme.colorScheme.primary
            )
        }
        Spacer(modifier = Modifier.height(spacing.spaceSmall))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = spacing.spaceSmall)
        ) {
            Text(
                text = state.product.name,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = state.product.brand,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
        Spacer(modifier = Modifier.height(spacing.spaceSmall))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = spacing.spaceSmall),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = state.product.price.toString(),
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(end = spacing.spaceSmall)
            )
            Button(onClick = { /*TODO*/ }) {
                Row {
                    Text(text = "+")
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "product image"
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(spacing.spaceSmall))
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = false, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ProductPortraitPreview() {
    EcommercemultimoduleTheme {
        ProductPortrait(
            state = ProductPortraitState(
                Product(
                    id = "123",
                    name = "Product Name",
                    brand = "Product Brand",
                    description = "Some description",
                    price = 9.99,
                    category = "category1",
                    tag = "featured"
                )
            ),
            config = ProductPortraitConfig()
        )
    }
}