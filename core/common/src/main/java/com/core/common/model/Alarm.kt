package com.core.common.model

data class Alarm(
    var isDone: Boolean = false,
    val time: String,
    val label: String?="",
    val medicineCnt: Int,
)
