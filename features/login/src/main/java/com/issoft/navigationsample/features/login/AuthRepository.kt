package com.issoft.navigationsample.features.login

import android.content.Context
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.UUID

sealed interface LoginResult {
    data class Success(val session: UserSession) : LoginResult
    data class Error(val message: String, val cause: Throwable? = null) : LoginResult
}

data class UserSession(
    val username: String,
    val token: String
)



interface AuthRepository {
    suspend fun login(username: String, password: String): LoginResult
    suspend fun logout()
    val session: Flow<UserSession?>
    suspend fun isAuthenticated() : Boolean
}

class AuthRepositoryImpl(
    private val prefs: UserPreferences,
    private val io: CoroutineDispatcher = Dispatchers.IO
) : AuthRepository {

    override val session: Flow<UserSession?> = prefs.session
    override suspend fun isAuthenticated() : Boolean{
        return session.firstOrNull() != null
    }

    override suspend fun login(username: String, password: String): LoginResult =
        withContext(io) {
            try {
                delay(2000)

                if (username.isBlank() || password.isBlank()) {
                    return@withContext LoginResult.Error("Username or password is empty")
                }
                if (password.length < 4) {
                    return@withContext LoginResult.Error("Invalid credentials")
                }

                val session = UserSession(
                    username = username,
                    token = UUID.randomUUID().toString()
                )
                prefs.saveSession(session)
                LoginResult.Success(session)
            } catch (t: Throwable) {
                LoginResult.Error("Login failed", t)
            }
        }

    override suspend fun logout() = withContext(io) {
        prefs.clearSession()
    }
}