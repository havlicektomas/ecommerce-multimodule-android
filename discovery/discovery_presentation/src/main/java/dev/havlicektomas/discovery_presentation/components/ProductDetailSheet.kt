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
import kotlinx.coroutines.Job

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailSheet(
    modifier: Modifier = Modifier,
    state: ProductPortraitState,
    config: ProductPortraitConfig,
    sheetState: SheetState,
    onDismissRequest: () -> Job
) {
    ModalBottomSheet(
        onDismissRequest = { onDismissRequest() },
        modifier = modifier,
        sheetState = sheetState
    ) {
        ProductDetail(
            modifier = modifier,
            state = state,
            config = config
        )
    }
}

@Composable
fun ProductDetail(
    modifier: Modifier = Modifier,
    state: ProductPortraitState,
    config: ProductPortraitConfig,
) {
    val spacing = LocalSpacing.current

    Column(
        modifier = modifier
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(240.dp)
                .background(color = Color.LightGray),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = state.product.imageUrl,
                contentDescription = "product image",
                contentScale = ContentScale.Crop
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
            Spacer(modifier = Modifier.height(spacing.spaceSmall))
            Text(
                text = state.product.description,
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
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = false, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ProductDetailSheetPreview() {
    EcommercemultimoduleTheme {
        ProductDetail(
            modifier = Modifier.fillMaxWidth(),
            state = ProductPortraitState(
                Product(
                    id = "123",
                    name = "Asparagus",
                    brand = "Brand",
                    description = "Some description",
                    price = 9.99,
                    category = "category1",
                    tag = "featured",
                    imageUrl = ""
                )
            ),
            config = ProductPortraitConfig()
        )
    }
}