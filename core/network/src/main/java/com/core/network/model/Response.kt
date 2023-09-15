package com.core.network.model


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Response(
    val code: Int,
    val message: String
)