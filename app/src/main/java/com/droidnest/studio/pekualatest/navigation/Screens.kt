package com.droidnest.studio.pekualatest.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.graphics.vector.ImageVector
import com.droidnest.studio.pekualatest.R
import kotlinx.serialization.Serializable

// Destinations.kt
sealed interface WelcomeDest {
    @Serializable
    object Welcome : WelcomeDest
}

// Splash.kt
sealed interface SplashDest {
    @Serializable
    object Splash : SplashDest
}

// Details.kt
sealed interface DetailsDest {
    @Serializable
    data class Details(
        val type: String,
        val imageUrl: String
    ) : DetailsDest
}// Details.kt

sealed interface PlaceDest {
    @Serializable
    data class Place(
        val placeName: String = "",
        val distance: String = "",
        val details: String = "",
        val imageUrl: String = "",
    ) : PlaceDest
}

sealed interface HomeDest {
    @Serializable
    object Root : HomeDest

    @Serializable
    object Home : HomeDest {
        override fun toString() = "home"
    }
    @Serializable
    object Service : HomeDest {
        override fun toString() = "service"
    }
    @Serializable
    object Info : HomeDest {
        override fun toString() = "info"
    }
}

enum class BottomNavItem(
    val iconResId: Int,
    val label: String,
    val route: String
) {
    Home(R.drawable.ic_home, "Home", "home"),
    Service(R.drawable.ic_service, "Service", "service"),
    Info(R.drawable.ic_info, "Info", "info")
}
