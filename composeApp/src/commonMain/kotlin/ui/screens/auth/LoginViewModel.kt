package ui.screens.home

import data.model.MasterUI
import data.model.auth.AuthData
import data.model.auth.TokenData
import data.model.project.ProjectData
import data.pref.DataStoreRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import network.usecase.AuthUseCase
import network.usecase.GetProjectsUseCase
import shared.Dispatcher

class LoginViewModel(
    private val authUseCase: AuthUseCase,
    private val dispatcher: Dispatcher,
    private val mainScope: CoroutineScope,
    private val dataStoreRepository: DataStoreRepository,
    private val getProjectsUseCase: GetProjectsUseCase
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

    private val _testToken: MutableStateFlow<String> = MutableStateFlow("")
    val testToken: StateFlow<String> = _testToken

    private fun readToken() {
        mainScope.launch(dispatcher.io) {
            dataStoreRepository.readToken().collect { savedToken ->
                _testToken.value = savedToken
            }
        }
    }

    fun saveToken(value: String) {
        mainScope.launch(dispatcher.io) {
            dataStoreRepository.saveToken(value)
        }
    }

    private val _proj: MutableStateFlow<MasterUI<List<ProjectData>>> = MutableStateFlow(MasterUI.Init)
    val proj: StateFlow<MasterUI<List<ProjectData>>> = _proj

    fun loadProjS() {
        mainScope.launch(dispatcher.io) {
            getProjectsUseCase.execute().collect { projData ->
                _proj.value = projData.toMasterUI()
            }
        }
    }

    init {
        readToken()
    }
}