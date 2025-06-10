package com.khizra.navigation_lib

object NavGraph {
    val DASHBOARD_SCREEN: ComposeDestination = ComposeDestination("dashboard")
    val PROFILE_SCREEN = ComposeDestination("profile", "dev://compose.navigation/profile")
    val CUSTOM_NAVIGATION_SCREEN: ComposeDestination = ComposeDestination("custom-navigation")

    val startDestination: String
        get() = DASHBOARD_SCREEN.route

    val deepLinkRouteMappings = buildMap {
        putAll(
            allDestinations.mapNotNull { destination ->
                destination.deepLink?.let { destination.route to destination.deepLink }
            }
        )
    }

    private val allDestinations
        get() = listOf(
            DASHBOARD_SCREEN,
            PROFILE_SCREEN,
            CUSTOM_NAVIGATION_SCREEN,
        )
}

infix fun <B> ComposeDestination.to(that: B): Pair<String, B> = Pair(route, that)
