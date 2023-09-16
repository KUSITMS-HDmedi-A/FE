package com.core.network.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AlarmAddResponse(
    val characterData: List<CharacterData>,
    val code: Int,
    val message: String
)