package com.droidnest.studio.pekualatest.presentation.welcome

import android.app.UiModeManager
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.UiMode
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.droidnest.studio.pekualatest.domain.model.OnBoardingPage
import com.droidnest.studio.pekualatest.navigation.HomeDest
import com.droidnest.studio.pekualatest.ui.theme.MyBanglaFontFamily


@Composable
fun WelcomeScreen(
    navController: NavHostController,
    welcomeViewModel: WelcomeViewModel = hiltViewModel()
) {
    val pages = listOf(
        OnBoardingPage.First,
        OnBoardingPage.Second,
        OnBoardingPage.Third
    )

    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { pages.size }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
           ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .weight(10f)
                .fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) { page ->
            OnBoardingPageScreen(onBoardingPage = pages[page])
        }

        PagerIndicator(
            pageCount = pages.size,
            currentPage = pagerState.currentPage,
            modifier = Modifier
                .padding(vertical = 16.dp)
                .weight(1f)
        )

        FinishButton(
            pagerState = pagerState,
            onClick = {
                navController.popBackStack()
                navController.navigate(HomeDest.Root)
                welcomeViewModel.saveOnBoardingState(completed = true)
            }
        )
    }
}

@Composable
fun OnBoardingPageScreen(onBoardingPage: OnBoardingPage) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp, vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,

    ) {
        Spacer(modifier = Modifier.height(20.dp))

        Image(
            painter = painterResource(id = onBoardingPage.image),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .fillMaxHeight(0.7f)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = onBoardingPage.title,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 16.dp),
            fontFamily = MyBanglaFontFamily
        )

        Text(
            text = onBoardingPage.description,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 12.dp),
            fontFamily = MyBanglaFontFamily

        )
    }
}

@Composable
fun PagerIndicator(
    pageCount: Int,
    currentPage: Int,
    modifier: Modifier = Modifier,
    activeColor: Color = MaterialTheme.colorScheme.primary,
    inactiveColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        repeat(pageCount) { index ->
            val dotSize by animateDpAsState(
                targetValue = if (index == currentPage) 12.dp else 8.dp,
                label = "dotSize"
            )
            Box(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .size(dotSize)
                    .clip(CircleShape)
                    .background(if (index == currentPage) activeColor else inactiveColor)
            )
        }
    }
}

@Composable
fun FinishButton(
    pagerState: PagerState,
    onClick: () -> Unit
) {
    val isLastPage = pagerState.currentPage == pagerState.pageCount - 1

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 40.dp, vertical = 32.dp)
            .height(56.dp),
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(visible = isLastPage) {
            Text(
                text = "Finish",
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(if (isLastPage) Color.Blue else Color.Transparent)
                    .clickable { onClick() }
                    .padding(vertical = 10.dp),
                color = Color.White,
                style = MaterialTheme.typography.titleLarge,
                fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                textAlign = TextAlign.Center
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewWelcomeScreen() {
    OnBoardingPageScreen(onBoardingPage = OnBoardingPage.First)
}


