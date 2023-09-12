package dev.havlicektomas.discovery_presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.havlicektomas.coreui.theme.EcommercemultimoduleTheme
import dev.havlicektomas.coreui.theme.LocalSpacing

data class ProductCategoryCardState(
    val name: String
)

data class ProductCategoryCardConfig(
    val onClick: () -> Unit = {}
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductCategoryCard(
    modifier: Modifier = Modifier,
    state: ProductCategoryCardState,
    config: ProductCategoryCardConfig
) {
    val spacing = LocalSpacing.current

    Card(
        onClick = { config.onClick() },
        modifier = modifier,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(spacing.spaceExtraLarge),
            contentAlignment = Alignment.Center
        ) {
            Text(text = state.name)
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = false, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ProductCategoryCardPreview() {
    EcommercemultimoduleTheme {
        ProductCategoryCard(
            state = ProductCategoryCardState(
                name = "Category"
            ),
            config = ProductCategoryCardConfig(
                onClick = {}
            )
        )
    }
}