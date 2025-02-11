package network.repository

import data.model.Resource
import data.model.auth.AuthData
import data.model.auth.TokenData
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun auth(authData: AuthData): Flow<Resource<TokenData>>
}