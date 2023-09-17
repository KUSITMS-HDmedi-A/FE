package com.core.common.model

data class WeekDate(
    val date: String, // 2023-09-17
    val korDay: String, // 일
    val engDay: String, // sunday
    var isSelected: Boolean = false
)
