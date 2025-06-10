package com.khizra.feature_profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.khizra.navigation_lib.LocalNavigator
import com.khizra.navigation_lib.Navigator

@Composable
fun ProfileContent(modifier: Modifier = Modifier) {
    val navigator = LocalNavigator.current
    val profileState = rememberProfileState(navigator)
    Box(modifier.fillMaxSize()) {
        Column(
            Modifier.align(Alignment.Center),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(profileState.title)
            Button(modifier = Modifier, onClick = profileState.buttonState.onClick) {
                Text(profileState.buttonState.text)
            }
        }
    }
}

@Composable
private fun rememberProfileState(navigator: Navigator) = remember {
    ProfileState(
        title = "Profile Page",
        buttonState = ProfileState.ButtonState(
            text = "Go Back",
            onClick = {
                navigator.navigateBack()
            }
        )
    )
}

data class ProfileState(
    val title: String,
    val buttonState: ButtonState
) {
    data class ButtonState(
        val text: String,
        val onClick: () -> Unit,
    )
}