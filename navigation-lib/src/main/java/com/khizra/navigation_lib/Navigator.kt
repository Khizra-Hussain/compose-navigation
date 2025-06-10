package com.khizra.navigation_lib

import android.net.Uri
import androidx.compose.runtime.compositionLocalOf

/**
 * Interface defining navigation operations in the app.
 */

val LocalNavigator = compositionLocalOf<Navigator> { error("No navigator provided") }

interface Navigator {
    /**
     * Navigate to a specific route
     * @param route The destination route
     * @param popUpTo Optional route to pop up to
     * @param inclusive Whether to include the popUpTo route in the pop operation
     */
    fun navigate(route: String, popUpTo: String? = null, inclusive: Boolean = false)

    /**
     * Navigate to a deep link
     * @param deepLink The deep link URI
     */
    fun navigateToDeepLink(deepLink: Uri)

    /**
     * Navigate to a custom destination
     * @param destination The custom destination to navigate to
     */
    fun navigateToCustomDestination(destination: CustomDestination)

    /**
     * Navigate back
     */
    fun navigateBack()
} 