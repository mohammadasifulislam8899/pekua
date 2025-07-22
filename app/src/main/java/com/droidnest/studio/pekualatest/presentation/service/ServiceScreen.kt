package com.droidnest.studio.pekualatest.presentation.service

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.droidnest.studio.pekualatest.presentation.ServiceViewModel
import com.droidnest.studio.pekualatest.presentation.components.LoadingAnim
import com.droidnest.studio.pekualatest.presentation.components.ServiceList
import com.droidnest.studio.pekualatest.ui.theme.MyBanglaFontFamily

@Composable
fun ServiceScreen(
    modifier: Modifier = Modifier,
    viewModel: ServiceViewModel = hiltViewModel(),
    paddingValues: PaddingValues = PaddingValues()
) {
    val services by viewModel.services.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues),
        contentAlignment = Alignment.Center
    ) {
        if (isLoading) {
            LoadingAnim()
        } else if (services.isEmpty()){
            Text(
                text = "কোন সার্ভিস পাওয়া যাচ্ছে না !",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
                fontFamily = MyBanglaFontFamily
            )
        }
        else {
            ServiceList(
                services = services,
                isFromServiceScreen = true
            )
        }
    }
}
