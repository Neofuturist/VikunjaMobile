package data.pref

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreRepository(private val dataStore: DataStore<Preferences>) {
    companion object {
        val TOKEN_KEY = stringPreferencesKey(name = "token")
    }

    suspend fun saveToken(token: String): Boolean = try {
        dataStore.edit { preferences ->
            preferences.set(key = TOKEN_KEY, value = token)
        }
        true
    } catch (e: Exception) {
        e.printStackTrace()
        false
    }

    fun readToken(): Flow<String> = dataStore.data
        .map { preferences ->
            preferences[TOKEN_KEY] ?: "hz"
        }
}