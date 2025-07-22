package com.droidnest.studio.pekualatest.presentation.splash

import android.content.res.Configuration
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily.Companion.Cursive
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.droidnest.studio.pekualatest.R
import com.droidnest.studio.pekualatest.navigation.HomeDest
import com.droidnest.studio.pekualatest.navigation.WelcomeDest
import com.droidnest.studio.pekualatest.ui.theme.Purple500
import com.droidnest.studio.pekualatest.ui.theme.Purple700


@Composable
fun SplashScreen(
    navController: NavHostController,
    splashViewModel: SplashViewModel = hiltViewModel()
) {
    val onBoardingCompleted by splashViewModel.onBoardingCompleted.collectAsState()
    val rotate = remember {
        Animatable(0f)
    }
    LaunchedEffect(true) {
        rotate.animateTo(
            targetValue = 360f,
            animationSpec = tween(
                durationMillis = 1000,
                delayMillis = 200
            )
        )
        navController.popBackStack()
        if (onBoardingCompleted){
            navController.navigate(HomeDest.Root)
        }else{
            navController.navigate(WelcomeDest.Welcome)
        }
    }
    Splash(rotation = rotate.value)
}

@Composable
fun Splash(
    rotation: Float
) {
    if (isSystemInDarkTheme()) {
        Box(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.pekua_logo),
                contentDescription = "App Logo",
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)
                    .rotate(rotation),
            )
            Text(
                text = "This app made by ASIF",
                color = Color.White,
                fontSize = 24.sp,
                fontFamily = Cursive,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(40.dp)
            )
        }
    } else {
        Box(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.pekua_logo),
                contentDescription ="ASIF",
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)
                    .rotate(rotation),
            )
            Text(
                text = "This app made by ASIF",
                color = Color.White,
                fontSize = 24.sp,
                fontFamily = Cursive,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(40.dp)
            )
        }
    }

}

@Preview
@Composable
fun SplashScreenPreview() {
    Splash(360f)
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SplashScreenDarkPreview() {
    Splash(360f)
}