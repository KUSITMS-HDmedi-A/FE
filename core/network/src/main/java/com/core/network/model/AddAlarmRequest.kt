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
    val time: String
)

// 예시
//{
//    "character":"이황",
//    "medicine" : "안녕",
//    "startDate" : "2023-09-01",
//    "endDate" : "2023-09-16",
//    "time" : "18:20:00",
//    "day" : [ "thursday"],
//    "label" : "뭐해?"
//}