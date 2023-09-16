package com.core.network.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Time(
    val hour: Int,
    val minute: Int,
    val nano: Int,
    val second: Int
)