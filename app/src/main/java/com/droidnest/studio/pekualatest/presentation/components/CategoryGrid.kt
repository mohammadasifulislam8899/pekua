package com.droidnest.studio.pekualatest.presentation.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.droidnest.studio.pekualatest.domain.model.Category
import com.droidnest.studio.pekualatest.ui.theme.MyBanglaFontFamily

@Composable
fun CategoryGrid(
    categories: List<Category>,
    isLoading: Boolean,
    onCategoryClick: (Category) -> Unit,
    showAll: Boolean
) {
    if (isLoading) {
        Box(
            modifier = Modifier.height(200.dp).fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            LoadingAnim()
        }
    } else if (categories.isEmpty()) {
        Box(
            modifier = Modifier.height(200.dp).fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "কোন ক্যাটাগরি পাওয়া যাচ্ছে না !",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
                fontFamily = MyBanglaFontFamily
            )
        }
    } else {

        val visibleItems = if (showAll) categories else categories.take(6)

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .wrapContentHeight()
                .animateContentSize(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(visibleItems.size) { index ->
                val category = visibleItems[index]
                val isEven = index % 2 == 0

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp), // slightly increased height for comfort
                    contentAlignment = if (isEven) Alignment.CenterStart else Alignment.CenterEnd
                ) {
                    CategoryItem(
                        category = category,
                        isImageLeft = isEven,
                        onClick = { onCategoryClick(category) },
                    )
                }
            }
        }

    }
}

@Composable
fun CategoryItem(
    category: Category,
    isImageLeft: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = MaterialTheme.colorScheme

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(10.dp),
        tonalElevation = 6.dp,
        color = colors.surfaceVariant
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            if (isImageLeft) {
                CategoryImage(
                    imageUrl = category.imageUrl,
                    modifier = Modifier.size(32.dp)   // <-- smaller image size here
                )
                Spacer(modifier = Modifier.width(16.dp))
                CategoryTexts(category.type, colors.onSurface, Modifier.weight(1f))
            } else {
                CategoryTexts(category.type, colors.onSurface, Modifier.weight(1f))
                Spacer(modifier = Modifier.width(16.dp))
                CategoryImage(
                    imageUrl = category.imageUrl,
                    modifier = Modifier.size(32.dp)  // <-- smaller image size here
                )
            }
        }
    }
}


@Composable
private fun CategoryImage(imageUrl: String, modifier: Modifier) {
    Box(
        modifier = modifier
    ) {
        AsyncImage(
            model = imageUrl.trim(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )
    }
}

@Composable
private fun CategoryTexts(
    text: String,
    color: androidx.compose.ui.graphics.Color,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = text.replaceFirstChar { it.uppercase() },
            color = color,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                lineHeight = 20.sp
            ),
            maxLines = 1,
            overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis,
            fontFamily = MyBanglaFontFamily
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Click for more",
            color = color.copy(alpha = 0.6f),
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryItemPreview() {
    val sampleCategory = Category(
        type = "Health Services",
        imageUrl = "https://cdn-icons-png.flaticon.com/512/2965/2965567.png"
    )
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        CategoryItem(
            category = sampleCategory,
            isImageLeft = true,
            onClick = {}
        )
        CategoryItem(
            category = sampleCategory,
            isImageLeft = false,
            onClick = {}
        )
    }
}
