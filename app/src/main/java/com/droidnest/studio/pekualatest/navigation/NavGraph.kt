// MainNavGraph.kt
package com.droidnest.studio.pekualatest.navigation

import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.droidnest.studio.pekualatest.presentation.details.DetailsScreen
import com.droidnest.studio.pekualatest.presentation.place.PlaceScreen
import com.droidnest.studio.pekualatest.presentation.splash.SplashScreen
import com.droidnest.studio.pekualatest.presentation.welcome.WelcomeScreen

@Composable
fun MainNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    NavHost(
        navController = navController,
        startDestination = SplashDest.Splash,
        modifier = modifier
    ) {
        composable<WelcomeDest.Welcome> {
            WelcomeScreen(
                navController = navController
            )
        }
        composable<SplashDest.Splash> {
            SplashScreen(
                navController = navController
            )
        }
        composable<HomeDest.Root> {
            BottomNavHost(
                onCategoryClick = {
                    navController.navigate(DetailsDest.Details(it.type, it.imageUrl))
                },
                onPlaceClick = {
                    navController.navigate(
                        PlaceDest.Place(
                            it.placeName,
                            it.distance,
                            it.details,
                            it.imageUrl
                        )
                    )
                }
            )
        }
        composable<DetailsDest.Details> {
            val args = it.toRoute<DetailsDest.Details>()
            val categoryName = args.type
            DetailsScreen(
                categoryName = categoryName
            ) {
                navController.popBackStack()
            }
        }
        composable<PlaceDest.Place> {
            val args = it.toRoute<PlaceDest.Place>()
            PlaceScreen(
                placeName = args.placeName,
                distance = args.distance,
                details = args.details,
                imageUrl = args.imageUrl
            ) {
                navController.popBackStack()
            }
        }
    }
}
