package network.usecase

import data.model.Resource
import data.model.auth.AuthData
import data.model.auth.TokenData
import kotlinx.coroutines.flow.Flow
import network.repository.UserRepository

class AuthUseCase(
    private val userRepository: UserRepository
) {
    suspend fun execute(authData: AuthData): Flow<Resource<TokenData>> {
        return userRepository.auth(authData = authData)
    }
}