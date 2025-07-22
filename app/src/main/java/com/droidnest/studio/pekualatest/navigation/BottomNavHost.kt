// BottomNavHost.kt
package com.droidnest.studio.pekualatest.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.droidnest.studio.pekualatest.domain.model.Category
import com.droidnest.studio.pekualatest.domain.model.PlaceModel
import com.droidnest.studio.pekualatest.presentation.home.HomeScreen
import com.droidnest.studio.pekualatest.presentation.info.InfoProfileScreen
import com.droidnest.studio.pekualatest.presentation.service.ServiceScreen

@Composable
fun BottomNavHost(
    onCategoryClick: (Category) -> Unit,
    onPlaceClick : (PlaceModel) -> Unit
) {
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    val items = BottomNavItem.entries

    Scaffold(
        modifier = Modifier.navigationBarsPadding(),
        bottomBar = {
            CustomBottomBar(
                items = items,
                currentRoute = currentRoute,
                onItemSelected = { item ->
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Home.route,
            modifier = Modifier
                .fillMaxSize()
        ) {
            composable(BottomNavItem.Home.route) {
                HomeScreen(
                    modifier = Modifier.fillMaxSize()
                    , onCategoryClick = {
                        onCategoryClick(it)
                    },
                    onPlaceClick = {
                        onPlaceClick(it)
                    }
                )
            }
            composable(BottomNavItem.Service.route) {
                ServiceScreen(paddingValues = paddingValues)
            }
            composable(BottomNavItem.Info.route) {
                InfoProfileScreen()
            }
        }
    }
}
