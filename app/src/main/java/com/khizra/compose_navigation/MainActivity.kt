package com.khizra.compose_navigation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.khizra.compose_navigation.ui.theme.ComposeNavigationTheme
import com.khizra.feature_dashboard.DashboardContent
import com.khizra.feature_profile.ProfileContent
import com.khizra.feature_dashboard.CustomNavigationContent
import com.khizra.navigation_lib.LocalNavigator
import com.khizra.navigation_lib.NavGraph.DASHBOARD_SCREEN
import com.khizra.navigation_lib.NavGraph.PROFILE_SCREEN
import com.khizra.navigation_lib.NavGraph.CUSTOM_NAVIGATION_SCREEN
import com.khizra.navigation_lib.NavGraph.deepLinkRouteMappings
import com.khizra.navigation_lib.NavGraph.startDestination
import com.khizra.navigation_lib.NavigationController.Companion.rememberNavigationController
import com.khizra.navigation_lib.NavigationHost
import com.khizra.navigation_lib.Navigator
import com.khizra.navigation_lib.to

class MainActivity : ComponentActivity() {
    private lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val context = LocalContext.current
            val navController = rememberNavController()
            navigator = rememberNavigationController(navController, context)
            BackHandler { navigator.navigateBack() }
            ComposeNavigationTheme {
                CompositionLocalProvider(LocalNavigator provides navigator) {
                    NavigationHost(
                        navController = navController,
                        startDestination = startDestination,
                        destinations = mapOf(
                            DASHBOARD_SCREEN to { DashboardContent() },
                            PROFILE_SCREEN to { ProfileContent() },
                            CUSTOM_NAVIGATION_SCREEN to { CustomNavigationContent() }
                        ),
                        deepLinks = deepLinkRouteMappings
                    )
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        if (intent.data != null) {
            navigator.navigateToDeepLink(intent.data!!)
        }
    }
}
