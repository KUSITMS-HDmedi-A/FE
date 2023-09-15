package com.kusitms.hdmedi.feature.signin.data.mapper


import com.core.network.model.TokenResponse
import com.kusitms.hdmedi.feature.signin.domain.model.Tokens
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

fun TokenResponse.toDomainTokens() : Flow<Tokens> = flowOf(
    Tokens(
        accessToken = this.accessToken,
        refreshToken = this.refreshToken
    )
)
