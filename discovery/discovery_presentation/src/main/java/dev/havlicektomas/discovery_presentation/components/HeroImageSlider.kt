package dev.havlicektomas.discovery_presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dev.havlicektomas.coreui.theme.EcommercemultimoduleTheme
import dev.havlicektomas.coreui.theme.LocalSpacing
import dev.havlicektomas.discovery_domain.model.HeroImage

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HeroImageSlider(
    modifier: Modifier = Modifier,
    images: List<HeroImage> = emptyList()
) {
    val spacing = LocalSpacing.current
    val pageCount = images.size
    val pagerState = rememberPagerState(pageCount = {
        pageCount
    })

    HorizontalPager(
        state = pagerState
    ) { page ->
        val imageUrl = images[page].imageUrl
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(color = Color.LightGray),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                modifier = modifier.fillMaxSize(),
                model = imageUrl,
                contentDescription = "product image",
                contentScale = ContentScale.Crop
            )
            Row(
                Modifier
                    .height(50.dp)
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(pageCount) { iteration ->
                    val color = if (pagerState.currentPage == iteration) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.tertiary
                    }
                    Box(
                        modifier = Modifier
                            .padding(spacing.spaceExtraSmall)
                            .clip(CircleShape)
                            .background(color)
                            .size(16.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = false, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HeroImageSliderPreview() {
    EcommercemultimoduleTheme {
        HeroImageSlider(
            images = listOf(
                HeroImage("1", "", ""),
                HeroImage("2", "", ""),
                HeroImage("3", "", "")
            )
        )
    }
}