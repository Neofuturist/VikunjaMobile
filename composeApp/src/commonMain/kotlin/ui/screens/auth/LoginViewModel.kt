package ui.screens.home

import data.model.MasterUI
import data.model.auth.AuthData
import data.model.auth.TokenData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import network.usecase.AuthUseCase
import shared.Dispatcher

class LoginViewModel(
    private val authUseCase: AuthUseCase,
    private val dispatcher: Dispatcher,
    private val mainScope: CoroutineScope
) {
    private val _token: MutableStateFlow<MasterUI<TokenData>> = MutableStateFlow(MasterUI.Init)
    val token: StateFlow<MasterUI<TokenData>> = _token

    fun auth() {
        mainScope.launch(dispatcher.io) {
            authUseCase.execute(
                authData = AuthData(
                    username = username.value,
                    password = password.value
                )
            ).collect { tokenData ->
                _token.value = tokenData.toMasterUI()
            }
        }
    }

    private val _username: MutableStateFlow<String> = MutableStateFlow("")
    val username: StateFlow<String> = _username

    fun setUsername(value: String) {
        _username.value = value
    }

    private val _password: MutableStateFlow<String> = MutableStateFlow("")
    val password: StateFlow<String> = _password

    fun setPassword(value: String) {
        _password.value = value
    }
}