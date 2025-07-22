package com.droidnest.studio.pekualatest.presentation.components

import android.content.Intent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*
import com.droidnest.studio.pekualatest.domain.model.Service
import androidx.core.net.toUri
import com.droidnest.studio.pekualatest.ui.theme.MyBanglaFontFamily

@Composable
fun ServiceCard(
    service: Service,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // 🔽 Left Side: Info
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    fontFamily = MyBanglaFontFamily,
                    text = service.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                ) {
                    Text(
                        fontFamily = MyBanglaFontFamily,
                        text = service.type,
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        fontFamily = MyBanglaFontFamily,
                        text = "📞 ",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        fontFamily = MyBanglaFontFamily,
                        text = service.phone,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        fontFamily = MyBanglaFontFamily,
                        text = "📍 ",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        fontFamily = MyBanglaFontFamily,
                        text = service.location,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            // 🔽 Right Side: Call Button with Lottie
            val composition by rememberLottieComposition(
                spec = LottieCompositionSpec.Asset("call.json")
            )
            val progress by animateLottieCompositionAsState(
                composition = composition,
                iterations = LottieConstants.IterateForever
            )

            Surface(
                shape = RoundedCornerShape(12.dp),
                tonalElevation = 2.dp,
                modifier = Modifier
                    .size(56.dp)
                    .clickable {
                        val intent = Intent(Intent.ACTION_DIAL).apply {
                            data = "tel:${service.phone}".toUri()
                        }
                        context.startActivity(intent)
                    },
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
            ) {
                LottieAnimation(
                    composition = composition,
                    progress = { progress },
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }
}


@Composable
fun ServiceList(
    services: List<Service>,
    isFromServiceScreen: Boolean
) {
    var searchQuery by remember { mutableStateOf("") }

    val filteredServices = services.filter {
        it.name.contains(searchQuery, ignoreCase = true) ||
                it.type.contains(searchQuery, ignoreCase = true) ||
                it.phone.contains(searchQuery, ignoreCase = true) ||
                it.location.contains(searchQuery, ignoreCase = true)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {

        if (isFromServiceScreen){

            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = {
                    Text(
                        fontFamily = MyBanglaFontFamily,
                        text = "সার্ভিস খুঁজতে সার্চ করুন..."
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(12.dp),
                singleLine = true,
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(onClick = { searchQuery = "" }) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Clear Search"
                            )
                        }
                    }
                }
            )
        }


        if (searchQuery.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
            ) {
                items(filteredServices) { service ->
                    ServiceCard(service = service)
                }
            }
        } else {
            AnimatedVisibility(
                visible = searchQuery.isEmpty(),
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    items(services) { service ->
                        ServiceCard(service = service)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ServiceCardPreview() {
    val sample = Service(
        id = "1",
        name = "Pekua Police Station",
        type = "Police",
        phone = "01812345678",
        location = "Pekua, Cox's Bazar",
        imageUrl = "" // no image needed now
    )
    ServiceCard(service = sample)
}
