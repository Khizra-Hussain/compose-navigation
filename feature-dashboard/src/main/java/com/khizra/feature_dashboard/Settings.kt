package com.khizra.feature_dashboard

import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.net.toUri
import com.khizra.navigation_lib.CustomDestination
import com.khizra.navigation_lib.LocalNavigator
import com.khizra.navigation_lib.Navigator

@Composable
fun CustomNavigationContent(modifier: Modifier = Modifier) {
    val navigator = LocalNavigator.current
    val state = rememberCustomNavigationState(navigator)
    Box(modifier.fillMaxSize()) {
        Text(text = state.title, modifier = Modifier.align(Alignment.Center))
        FooterContent(
            state.footerState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .navigationBarsPadding()
        )
    }
}

@Composable
fun rememberCustomNavigationState(navigator: Navigator) = remember {
    CustomNavigationState(
        title = "Custom Navigation Page",
        footerState = FooterState(
            first = ButtonState(
                text = "Open browser",
                onClick = {
                    navigator.navigateToCustomDestination(
                        CustomDestination.IntentDestination(
                            Intent(
                                Intent.ACTION_VIEW,
                                "https://sports.yahoo.com/".toUri()
                            )
                        )
                    )
                }
            ),
            second = ButtonState(
                text = "Go back",
                onClick = {
                    navigator.navigateBack()
                }
            )
        )
    )
}

data class CustomNavigationState(
    val title: String,
    val footerState: FooterState,
)