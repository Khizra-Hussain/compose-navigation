package com.khizra.navigation_lib

import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable

/**
 * Sealed class representing different types of navigation destinations
 */
sealed class CustomDestination {
    /**
     * A composable destination with a route
     */
    data class ComposableDestination(
        val route: String,
        val popUpTo: String? = null,
        val inclusive: Boolean = false,
    ) : CustomDestination()

    /**
     * A deep link destination
     */
    data class DeepLinkDestination(
        val uri: Uri
    ) : CustomDestination()

    /**
     * An external intent destination
     */
    data class IntentDestination(
        val intent: Intent
    ) : CustomDestination()
} 