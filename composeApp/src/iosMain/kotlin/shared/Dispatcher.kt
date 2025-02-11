package shared

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

internal class IosDispatcher: Dispatcher {
    override val io: CoroutineDispatcher
        get() = Dispatchers.IO
}

internal actual fun provideDispatcher(): Dispatcher = IosDispatcher()