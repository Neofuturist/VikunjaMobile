package network.repository

import data.model.Resource
import data.model.auth.AuthData
import data.model.auth.TokenData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import network.HttpUtil
import network.ktor.UserAuthApiService

class UserRepositoryImpl(
    private val httpUtil: HttpUtil,
    private val userApiService: UserAuthApiService
): UserRepository {
    override suspend fun auth(authData: AuthData): Flow<Resource<TokenData>> = flow {
        emit(Resource.Loading)
        emit(httpUtil.checkResponse(userApiService.auth(authData)))
    }
}