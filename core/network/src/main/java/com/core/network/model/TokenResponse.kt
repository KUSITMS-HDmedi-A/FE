package com.core.network.model


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TokenResponse(
    val accessToken: String,
    val code: Int,
    val message: String,
    val refreshToken: String
)