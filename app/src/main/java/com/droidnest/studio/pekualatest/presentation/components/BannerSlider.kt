package com.droidnest.studio.pekualatest.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.tbuonomo.viewpagerdotsindicator.compose.DotsIndicator
import com.tbuonomo.viewpagerdotsindicator.compose.model.DotGraphic
import com.tbuonomo.viewpagerdotsindicator.compose.type.ShiftIndicatorType
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import com.droidnest.studio.pekualatest.ui.theme.MyBanglaFontFamily
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun BannerSlider(
    banners: List<String>,
    isLoading: Boolean,
    modifier: Modifier = Modifier,
    autoSlideDuration: Long = 2500L
) {
    val pagerState = rememberPagerState { banners.size }

    LaunchedEffect(key1 = banners.size) {
        if (banners.isNotEmpty()) {
            while (true) {
                delay(autoSlideDuration)
                val nextPage = (pagerState.currentPage + 1) % banners.size
                pagerState.animateScrollToPage(nextPage)
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(210.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(210.dp),
                contentAlignment = Alignment.Center
            ) {
                LoadingAnim()
            }
        }else if (banners.isEmpty()){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(210.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "কোন ব্যানার পাওয়া যাচ্ছে না !",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontFamily = MyBanglaFontFamily
                )
            }
        }
        else {
            HorizontalPager(
                state = pagerState,
                pageSpacing = 16.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .padding(horizontal = 16.dp)
            ) { page ->
                AsyncImage(
                    model = banners[page],
                    contentDescription = "Banner $page",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.LightGray)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            DotsIndicator(
                dotCount = banners.size,
                pagerState = pagerState,
                type = ShiftIndicatorType(
                    DotGraphic(
                        color = MaterialTheme.colorScheme.primary,
                        size = 6.dp
                    )
                ),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}
