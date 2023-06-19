package dev.havlicektomas.discovery_presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.havlicektomas.coreui.theme.EcommercemultimoduleTheme
import dev.havlicektomas.coreui.theme.LocalSpacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopAppBar(
    modifier: Modifier = Modifier
) {
    val spacing = LocalSpacing.current

    TopAppBar(
        title = {
            Row(
                modifier = Modifier
            ) {
                Column {
                    Text(
                        text = "Username"
                    )
                }
            }
        },
        modifier = modifier,
        navigationIcon = {
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "account"
                )
            }
        },
        actions = {
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "cart"
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun MainTopAppBarPreview() {
    EcommercemultimoduleTheme {
        MainTopAppBar()
    }
}