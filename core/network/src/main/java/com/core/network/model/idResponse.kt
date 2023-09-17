package com.core.network.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class idResponse(
    val id: Int
)
