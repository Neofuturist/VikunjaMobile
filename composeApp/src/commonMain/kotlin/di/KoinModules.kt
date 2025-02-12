package di

import data.pref.DataStoreRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import network.HttpUtil
import network.ktor.KtorApi
import network.ktor.UserAuthApiService
import network.repository.UserRepository
import network.repository.UserRepositoryImpl
import network.usecase.AuthUseCase
import org.koin.dsl.module
import shared.dataStoreModule
import shared.provideDispatcher
import ui.screens.home.HomeViewModel
import ui.screens.home.LoginViewModel

object KoinModules {
    fun appModule() = mutableListOf(
        ktorModule,
        viewModelModule,
        httpUtilModule,
        repoModule,
        useCaseModule,
        dataStoreModule
    )

    private val viewModelModule = module {
        single { HomeViewModel() }
        single { LoginViewModel(get(), get(), get(), get()) }
    }

    private val ktorModule = module {
        single { KtorApi() }
        single { UserAuthApiService(get()) }
        single { provideDispatcher() }
        single { CoroutineScope(Dispatchers.IO) }
    }

    private val httpUtilModule = module {
        single { HttpUtil() }
    }

    private val repoModule = module {
        single<UserRepository> { UserRepositoryImpl(get(), get()) }
        single { DataStoreRepository(get()) }
    }

    private val useCaseModule = module {
        single { AuthUseCase(get()) }
    }
}