package ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import data.model.MasterUI
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

    val testToken by viewModel.testToken.collectAsState()
    val projS by viewModel.proj.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
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
            onClick = {
                (viewModel::auth)()
            }
        ) {
            Text(LocalTheme.current.strings.auth)
        }
        Button(
            onClick = {
                if (token is MasterUI.Success) {
                    viewModel.saveToken((token as MasterUI.Success).data.token ?: "")
                }
            }
        ) {
            Text(("save test token"))
        }
        Text("Saved token: $testToken")
        MasterView(
            state = token,
            errorView = {},
            loadingView = {}
        ) { tokenData ->
            Text(text = tokenData.token ?: "null")
        }
        Button(
            onClick = {
                viewModel.loadProjS()
            }
        ) {
            Text("Load projects")
        }
        MasterView(
            state = projS,
            errorView = {},
            loadingView = {}
        ) { projects ->
            Text(text = projects.toString())
        }
    }
}