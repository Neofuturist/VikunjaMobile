package ui.screens.app

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import di.KoinModules
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication
import ui.screens.home.Login

@Composable
@Preview
fun App() {
    KoinApplication(application = {
        modules(KoinModules.appModule())
    }) {
        MaterialTheme {
            Login()
        }
    }
}