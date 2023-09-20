package dev.havlicektomas.discovery_presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dev.havlicektomas.coreui.theme.EcommercemultimoduleTheme
import dev.havlicektomas.coreui.theme.LocalSpacing
import dev.havlicektomas.discovery_domain.model.Product
import dev.havlicektomas.discovery_presentation.components.preview_util.loremIpsumText
import kotlinx.coroutines.Job

data class ProductDetailState(
    val product: Product
)

data class ProductDetailConfig(
    val elements: List<String> = listOf("title", "brand", "description", "price"),
    val onFavouriteClick: () -> Unit = {},
    val onAddToCartClick: () -> Unit = {}
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailSheet(
    modifier: Modifier = Modifier,
    state: ProductDetailState,
    config: ProductDetailConfig,
    sheetState: SheetState,
    onDismissRequest: () -> Job
) {
    ModalBottomSheet(
        onDismissRequest = { onDismissRequest() },
        modifier = modifier
            .fillMaxHeight()
            .widthIn(440.dp),
        sheetState = sheetState
    ) {
        ProductDetail(
            state = state,
            config = config
        )
    }
}

@Composable
fun ProductDetail(
    state: ProductDetailState,
    config: ProductDetailConfig,
) {
    val spacing = LocalSpacing.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(bottom = 80.dp)
    ) {
        item {
            AsyncImage(
                model = state.product.imageUrl,
                contentDescription = "product image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(320.dp)
                    .background(color = Color.LightGray),
                contentScale = ContentScale.Crop
            )
        }
        config.elements.forEach {element ->
            when (element) {
                "title" -> {
                    item {
                        Text(
                            text = state.product.name,
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.padding(
                                start = spacing.spaceMedium,
                                end = spacing.spaceMedium,
                                top = spacing.spaceMedium,
                                bottom = 0.dp
                            )
                        )
                    }
                }
                "brand" -> {
                    item {
                        Text(
                            text = state.product.brand,
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.padding(
                                vertical = spacing.spaceExtraSmall,
                                horizontal = spacing.spaceMedium
                            )
                        )
                    }
                }
                "description" -> {
                    item {
                        Text(
                            text = loremIpsumText,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.padding(spacing.spaceMedium)
                        )
                    }
                }
                "price" -> {
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(spacing.spaceMedium),
                            horizontalAlignment = Alignment.End
                        ) {
                            Text(
                                text = "$ ${state.product.price}",
                                style = MaterialTheme.typography.headlineSmall,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Button(onClick = { config.onAddToCartClick() }) {
                                Icon(
                                    imageVector = Icons.Default.ShoppingCart,
                                    contentDescription = "cart icon"
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = false, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ProductDetailSheetPreview() {
    EcommercemultimoduleTheme {
        ProductDetail(
            state = ProductDetailState(
                Product(
                    id = "123",
                    name = "Asparagus",
                    brand = "Brand",
                    description = loremIpsumText,
                    price = 9.99,
                    category = "category1",
                    tag = "featured",
                    imageUrl = ""
                )
            ),
            config = ProductDetailConfig()
        )
    }
}