package com.core.network.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AddAlarmRequest(
    val character: String,
    val day: List<String>,
    val endDate: String,
    val label: String,
    val medicine: String,
    val startDate: String,
    val time: Time
)