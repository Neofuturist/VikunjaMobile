package data.pref

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import org.lighthousegames.logging.logging

class DataStoreRepository(private val dataStore: DataStore<Preferences>) {
    private val TAG = "DataStoreRepo"

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
            preferences[TOKEN_KEY] ?: ""
        }

    fun readTokenString(): String = runBlocking {
        try {
            dataStore.data.map {
                it[TOKEN_KEY] ?: ""
            }.first()
        } catch (e: Exception) {
            logging(TAG).e { e.message }
            return@runBlocking ""
        }
    }
}