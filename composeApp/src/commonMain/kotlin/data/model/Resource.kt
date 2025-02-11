package data.model

import androidx.compose.runtime.Immutable
import org.koin.core.component.KoinComponent

@Immutable
sealed class Resource<out T> {
    data class Success<T>(val data: T) : Resource<T>()
    data object Loading : Resource<Nothing>()
    data class Error(val error: Throwable) : Resource<Nothing>()

    inline fun <reified T> toMasterUI(callback: ((T) -> Unit) = {}): MasterUI<T> {
        return when (this) {
            is Loading -> MasterUI.Loading
            is Error -> {
                val error = MasterUI.Error(message = error.message ?: "")
                error
            }
            is Success -> {
                callback(data as T)
                MasterUI.Success(data as T)
            }
        }
    }
}

@Immutable
sealed class MasterUI<out T>: KoinComponent {
    data object Init : MasterUI<Nothing>()
    data class Success<T>(val data: T) : MasterUI<T>()
    data object Loading : MasterUI<Nothing>()
    data class Error(val message: String) : MasterUI<Nothing>()
}