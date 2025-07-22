package com.droidnest.studio.pekualatest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.droidnest.studio.pekualatest.navigation.MainNavGraph
import com.droidnest.studio.pekualatest.ui.theme.PekuaLatestTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            PekuaLatestTheme {
                val navController = rememberNavController()
                MainNavGraph(navController, Modifier.fillMaxSize())
            }
        }
    }

}
