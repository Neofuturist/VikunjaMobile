package ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.koin.compose.koinInject
import ui.components.MasterView
import ui.theme.LocalTheme

@Composable
fun Login(
    viewModel: LoginViewModel = koinInject()
) {
    val token by viewModel.token.collectAsState()
    val username by viewModel.username.collectAsState()
    val password by viewModel.password.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = username,
            onValueChange = { (viewModel::setUsername)(it) }
        )
        TextField(
            value = password,
            onValueChange = { (viewModel::setPassword)(it) }
        )
        Button(
            onClick = { (viewModel::auth)() }
        ) {
            Text(LocalTheme.current.strings.auth)
        }
        MasterView(
            state = token,
            errorView = {},
            loadingView = {}
        ) { tokenData ->
            Text(text = tokenData.token ?: "null")
        }
    }
}