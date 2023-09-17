package com.core.network.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterData(
    val characterName: String,
    val medicine: List<Medicine>
)