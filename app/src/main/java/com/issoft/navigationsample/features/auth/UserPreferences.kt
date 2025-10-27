package com.issoft.navigationsample.features.auth

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "user_prefs")
class UserPreferences(private val context: Context) {
    private val KEY_USERNAME = stringPreferencesKey("username")
    private val KEY_TOKEN = stringPreferencesKey("token")

    val session: Flow<UserSession?> =
        context.dataStore.data.map { prefs ->
            val u = prefs[KEY_USERNAME]
            val t = prefs[KEY_TOKEN]
            if (u != null && t != null) UserSession(u, t) else null
        }

    suspend fun saveSession(session: UserSession) {
        context.dataStore.edit { prefs ->
            prefs[KEY_USERNAME] = session.username
            prefs[KEY_TOKEN] = session.token
        }
    }

    suspend fun clearSession() {
        context.dataStore.edit { it.clear() }
    }
}