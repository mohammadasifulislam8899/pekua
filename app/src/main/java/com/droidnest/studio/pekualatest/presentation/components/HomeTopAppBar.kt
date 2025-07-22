package com.droidnest.studio.pekualatest.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.droidnest.studio.pekualatest.R
import com.droidnest.studio.pekualatest.ui.theme.BebasNeue

@Composable
@OptIn(ExperimentalMaterial3Api::class) fun HomeTopAppBar() {
    TopAppBar(
        title = {
            Text("PEKUA", fontFamily = BebasNeue, fontSize = 24.sp)
        },
        navigationIcon = {
            IconButton(onClick = { /* TODO */ }) {
                Image(
                    painter = painterResource(id = R.drawable.pekua_logo),
                    contentDescription = "Profile",
                    modifier = Modifier.size(32.dp)
                )
            }
        },
        actions = {
            IconButton(onClick = { /* TODO */ }) {
                Image(
                    painter = painterResource(id = R.drawable.notification),
                    contentDescription = "Notification",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    )
}