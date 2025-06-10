package com.khizra.navigation_lib

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

/**
 * A custom navigation host that supports regular navigation, deep links, and custom destinations
 */
@Composable
fun NavigationHost(
    navController: NavHostController,
    startDestination: String,
    destinations: Map<String, @Composable () -> Unit>,
    deepLinks: Map<String, String> = emptyMap()
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        // Add regular composable destinations
        destinations.forEach { (route, content) ->
            composable(route) {
                content()
            }
        }

        // Add deep link destinations
        deepLinks.forEach { (route, deepLink) ->
            composable(
                route = route,
                deepLinks = listOf(
                    androidx.navigation.navDeepLink { uriPattern = deepLink }
                )
            ) {
                destinations[route]?.invoke()
            }
        }
    }
} 