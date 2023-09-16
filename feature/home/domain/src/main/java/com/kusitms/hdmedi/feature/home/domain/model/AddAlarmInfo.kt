package com.kusitms.hdmedi.feature.home.domain.model


data class AddAlarmInfo(
    val character: String,
    val day: List<String>,
    val endDate: String,
    val label: String? = "",
    val medicine: String,
    val startDate: String,
    val time: String,
)

