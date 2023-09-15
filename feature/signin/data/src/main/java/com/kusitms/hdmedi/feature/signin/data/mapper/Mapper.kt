package com.kusitms.hdmedi.feature.signin.data.mapper


import com.core.network.model.TokenResponse
import com.kusitms.hdmedi.feature.signin.domain.model.Tokens

fun TokenResponse.toDomainTokens() : Tokens =
    Tokens(
        accessToken = this.accessToken,
        refreshToken = this.refreshToken
    )
