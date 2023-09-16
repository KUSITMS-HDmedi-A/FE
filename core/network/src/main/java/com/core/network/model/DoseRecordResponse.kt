package com.core.network.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DoseRecordResponse(
    val characterList: List<Character>,
    val code: Int,
    val message: String
)

@JsonClass(generateAdapter = true)
data class Character(
    val doseAlarmList: List<DoseAlarm>,
    val name: String
)

@JsonClass(generateAdapter = true)
data class DoseAlarm(
    val count: Int,
    val doseSign: Boolean,
    val label: String,
    val record: String,
    val time: String
)