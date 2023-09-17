package com.kusitms.hdmedi.feature.signin.domain.repo

import com.kusitms.hdmedi.feature.signin.domain.model.Tokens
import kotlinx.coroutines.flow.Flow

interface SigninRepository {
    suspend fun saveAccessToken(token: String): Flow<String?>
    suspend fun requestLogin(): Flow<Tokens>
    suspend fun saveJwtTokens(accessToken: String, refreshToken: String): Flow<String?>
    fun getFCMToken(): String?
    suspend fun sendFCMToken(tokens: String)
}