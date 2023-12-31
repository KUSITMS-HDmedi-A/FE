package com.kusitms.hdmedi.feature.signin.data.repo

import android.content.SharedPreferences
import com.core.common.SharedPreferenceManager
import com.core.network.datasource.NetworkDataSource
import com.core.network.model.FcmTokenRequest
import com.core.network.utils.TokenDataStore
import com.kusitms.hdmedi.feature.signin.data.mapper.toDomainTokens
import com.kusitms.hdmedi.feature.signin.domain.model.Tokens
import com.kusitms.hdmedi.feature.signin.domain.repo.SigninRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SigninRepositoryImpl @Inject constructor(
    private val dataSource: NetworkDataSource,
    private val tokenSource: TokenDataStore,
    private val sharedPreferences: SharedPreferenceManager
): SigninRepository {
    override suspend fun saveAccessToken(token: String): Flow<String?> {
        tokenSource.saveAccessToken(token)
        return tokenSource.getAccessToken().flowOn(Dispatchers.IO)
    }

    override suspend fun requestLogin(): Flow<Tokens> =
        dataSource.kakaoLogin().toDomainTokens().flowOn(Dispatchers.IO)

    override suspend fun saveJwtTokens(accessToken: String, refreshToken: String): Flow<String?> {
        tokenSource.saveAccessToken(accessToken)
        tokenSource.saveRefreshToken(refreshToken)
        return tokenSource.getAccessToken().flowOn(Dispatchers.IO)
    }

    override fun getFCMToken(): String? {
        return sharedPreferences.getFCMToken()
    }

    override suspend fun sendFCMToken(token: String) {
        dataSource.pathFCMToken(FcmTokenRequest(token))
    }
}