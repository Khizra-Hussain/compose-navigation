package com.khizra.navigation_lib

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController

/**
 * Implementation of the Navigator interface using NavController
 */
class NavigationController(
    private val navController: NavHostController,
    private val context: Context
) : Navigator {

    override fun navigate(route: String, popUpTo: String?, inclusive: Boolean) {
        if (popUpTo != null) {
            navController.navigate(route) {
                popUpTo(popUpTo) {
                    this.inclusive = inclusive
                }
            }
        } else {
            navController.navigate(route)
        }
    }

    override fun navigateToDeepLink(deepLink: Uri) {
        navController.navigate(deepLink)
    }

    override fun navigateToCustomDestination(destination: CustomDestination) {
        when (destination) {
            is CustomDestination.ComposableDestination -> {
                navigate(destination.route, destination.popUpTo, destination.inclusive)
            }

            is CustomDestination.DeepLinkDestination -> {
                navigateToDeepLink(destination.uri)
            }

            is CustomDestination.IntentDestination -> {
                context.startActivity(destination.intent)
            }
        }
    }

    override fun navigateBack() {
        // close activity if we couldn't pop back
        if (!navController.popBackStack()) {
            context.findActivity()?.finish()
        }
    }

    companion object {
        @Composable
        fun rememberNavigationController(
            navController: NavHostController,
            context: Context
        ): NavigationController {
            return remember(navController, context) {
                NavigationController(navController, context)
            }
        }
    }
}

private fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}