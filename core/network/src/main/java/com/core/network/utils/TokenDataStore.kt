package com.core.network.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Named

class TokenDataStore @Inject constructor(
    @Named("token_preferences")private val dataStore: DataStore<Preferences>
) {
    object PreferenceKey {
        val ACCESS_TOKEN_KEY = stringPreferencesKey("ACCESS_TOKEN")
        val REFRESH_TOKEN_KEY = stringPreferencesKey("REFRESH_TOKEN")
    }

    suspend fun getToken(): String? = getAccessToken().first()


    fun getAccessToken(): Flow<String?> {
        return dataStore.data.map { prefs ->
            prefs[PreferenceKey.ACCESS_TOKEN_KEY]
        }
    }

    suspend fun saveAccessToken(token: String){
        dataStore.edit { prefs ->
            prefs[PreferenceKey.ACCESS_TOKEN_KEY] = token
        }
    }

    suspend fun deleteAccessToken(){
        dataStore.edit { prefs ->
            prefs.remove(PreferenceKey.ACCESS_TOKEN_KEY)
        }
    }

    fun getRefreshToken(): Flow<String?> {
        return dataStore.data.map { prefs ->
            prefs[PreferenceKey.REFRESH_TOKEN_KEY]
        }
    }

    suspend fun saveRefreshToken(token: String){
        dataStore.edit { prefs ->
            prefs[PreferenceKey.REFRESH_TOKEN_KEY] = token
        }
    }

    suspend fun deleteRefreshToken(){
        dataStore.edit { prefs ->
            prefs.remove(PreferenceKey.REFRESH_TOKEN_KEY)
        }
    }
}