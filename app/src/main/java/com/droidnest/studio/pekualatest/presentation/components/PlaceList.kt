package com.droidnest.studio.pekualatest.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.droidnest.studio.pekualatest.domain.model.PlaceModel
import com.droidnest.studio.pekualatest.ui.theme.MyBanglaFontFamily

@Composable
fun PlaceCard(
    place: PlaceModel,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .width(220.dp)
            .height(180.dp)
            .clickable { onClick() }, // 🔥 Click handler
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            // 🔥 SharedElement wrapper
            androidx.compose.animation.Crossfade(targetState = place.imageUrl) { image ->
                AsyncImage(
                    model = image,
                    contentDescription = place.placeName,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .clip(RoundedCornerShape(12.dp))
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                fontFamily = MyBanglaFontFamily,
                text = place.placeName,
                style = MaterialTheme.typography.titleMedium.copy(
                    color = MaterialTheme.colorScheme.onSurface
                ),
                fontWeight = FontWeight.Bold
            )
            Text(
                fontFamily = MyBanglaFontFamily,
                text = place.details,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                ),
                maxLines = 2
            )
        }
    }
}


@Composable
fun PlaceList(
    places: List<PlaceModel>,
    isLoading: Boolean,
    onPlaceClick: (PlaceModel) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        if (isLoading ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentAlignment = Alignment.Center
            ) {
                LoadingAnim()
            }
        }else if (places.isEmpty()){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "কোন জায়গা পাওয়া যাচ্ছে না !",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontFamily = MyBanglaFontFamily
                )
            }
        }
        else {
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(places) { place ->
                    PlaceCard(
                        place = place,
                        onClick = { onPlaceClick(place) }
                    )
                }
            }
        }
    }
}
