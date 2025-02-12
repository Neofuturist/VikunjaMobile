package shared

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import org.koin.core.module.Module

expect fun createDataStore(context: Any? = null): DataStore<Preferences>

expect val dataStoreModule: Module