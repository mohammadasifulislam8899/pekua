package com.droidnest.studio.pekualatest.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.droidnest.studio.pekualatest.domain.model.Category
import com.droidnest.studio.pekualatest.domain.model.PlaceModel
import com.droidnest.studio.pekualatest.presentation.components.*
import com.droidnest.studio.pekualatest.ui.theme.InterFont
import com.droidnest.studio.pekualatest.ui.theme.MyBanglaFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    onCategoryClick: (Category) -> Unit,
    onPlaceClick: (PlaceModel) -> Unit // 🔥 eta add koro
)
 {
    val categories by viewModel.categories.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    val places by viewModel.places.collectAsState()
    val isPlaceLoading by viewModel.isPlaceLoading.collectAsState()

    val banners by viewModel.banners.collectAsState()
    val isBannerLoading by viewModel.isBannerLoading.collectAsState()

    var showAll by remember { mutableStateOf(false) }


    Scaffold(
        modifier = modifier,
        topBar = { HomeTopAppBar() }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(bottom = 75.dp)
        ) {
            item {
                BannerSlider(
                    banners = banners,
                    isLoading = isBannerLoading
                )
            }
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Categories :",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = MyBanglaFontFamily
                    )
                    Text(
                        text = if (showAll) "Show less" else "See all",
                        style = MaterialTheme.typography.labelLarge.copy(
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 14.sp
                        ),
                        modifier = Modifier.clickable { showAll = !showAll }
                    )
                }
            }

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 500.dp)
                ) {
                    CategoryGrid(
                        categories = categories,
                        isLoading = isLoading,
                        onCategoryClick = onCategoryClick,
                        showAll = showAll
                    )
                }
            }
            item {
                Text(
                    text = "Popular Places:",
                    modifier = Modifier.padding(horizontal = 16.dp),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = InterFont,
                    color = MaterialTheme.colorScheme.onSurface,

                )
            }
            item {
                PlaceList(
                    places = places,
                    isLoading = isPlaceLoading,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp),
                    onPlaceClick = onPlaceClick // 🔥 eta pathao
                )
            }
        }
    }
}
