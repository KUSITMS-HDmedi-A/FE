package com.core.network.model


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseDto(
    val code: Int,
    val message: String
)