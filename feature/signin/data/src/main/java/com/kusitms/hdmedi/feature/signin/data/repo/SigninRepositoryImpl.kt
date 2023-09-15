package com.kusitms.hdmedi.feature.signin.data.repo

import com.core.network.datasource.NetworkDataSource
import com.core.network.utils.TokenDataStore
import com.kusitms.hdmedi.feature.signin.data.mapper.toDomainTokens
import com.kusitms.hdmedi.feature.signin.domain.model.Tokens
import com.kusitms.hdmedi.feature.signin.domain.repo.SigninRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SigninRepositoryImpl @Inject constructor(
    private val dataSource: NetworkDataSource,
    private val tokenSource: TokenDataStore
): SigninRepository {
    override suspend fun saveSocialToken(token: String): Flow<String?> {
        tokenSource.saveSocialToken(token)
        return tokenSource.getSocialToken()
    }

    override suspend fun requestLogin(): Tokens =
        dataSource.kakaoLogin().toDomainTokens()

    override suspend fun saveJwtTokens(accessToken: String, refreshToken: String): Flow<String?> {
        tokenSource.saveAccessToken(accessToken)
        tokenSource.saveRefreshToken(refreshToken)
        return tokenSource.getAccessToken()
    }
}