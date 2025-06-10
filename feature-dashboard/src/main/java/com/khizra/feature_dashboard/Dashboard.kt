package com.khizra.feature_dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.khizra.navigation_lib.LocalNavigator
import com.khizra.navigation_lib.NavGraph
import com.khizra.navigation_lib.Navigator

@Composable
fun DashboardContent(modifier: Modifier = Modifier) {
    val navigator = LocalNavigator.current
    val state = rememberDashboardState(navigator)
    Box(modifier.fillMaxSize()) {
        MiddleContent(state.middle, modifier = Modifier.align(Alignment.Center))
        FooterContent(
            state.footer,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .navigationBarsPadding()
        )
    }
}


@Composable
fun FooterContent(footer: FooterState, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        ButtonUi(footer.first, Modifier.weight(1f))
        ButtonUi(footer.second, Modifier.weight(1f))
    }
}

@Composable
private fun MiddleContent(state: MiddleState, modifier: Modifier = Modifier) {
    Column(modifier) {
        Text(state.title)
        ButtonUi(state.buttonState)
    }
}

@Composable
private fun rememberDashboardState(navigator: Navigator) = remember {
    DashboardState(
        middle = MiddleState(
            title = "Dashboard Page",
            buttonState = ButtonState(
                text = "Custom Navigation",
                onClick = {
                    navigator.navigate(NavGraph.CUSTOM_NAVIGATION_SCREEN.route)
                }
            )
        ),
        footer = FooterState(
            first = ButtonState(
                text = "With route",
                onClick = {
                    navigator.navigate(NavGraph.PROFILE_SCREEN.route)
                }),
            second = ButtonState(
                text = "With deeplink",
                onClick = {
                    navigator.navigateToDeepLink(NavGraph.PROFILE_SCREEN.deepLink!!.toUri())
                }
            )
        )
    )
}

@Composable
private fun ButtonUi(state: ButtonState, modifier: Modifier = Modifier) {
    Button(modifier = modifier, onClick = state.onClick) {
        Text(state.text)
    }
}

data class DashboardState(
    val middle: MiddleState,
    val footer: FooterState
)

data class MiddleState(
    val title: String,
    val buttonState: ButtonState
)

data class FooterState(
    val first: ButtonState,
    val second: ButtonState,
)

data class ButtonState(
    val text: String,
    val onClick: () -> Unit,
)
