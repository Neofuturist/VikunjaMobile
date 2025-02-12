package data.pref

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import okio.Path.Companion.toPath

internal const val dataStoreFileName = "settings.preferences_pb"

object AppSetting {
    private lateinit var dataStore: DataStore<Preferences>

    fun getDataStore(producePath: () -> String): DataStore<Preferences> {
        return if (::dataStore.isInitialized) {
            dataStore
        } else {
            PreferenceDataStoreFactory.createWithPath(produceFile = {producePath().toPath()})
                .also {
                    dataStore = it
                }
        }
    }
}