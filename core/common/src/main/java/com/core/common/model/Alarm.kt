package com.core.common.model

data class Alarm(
    val isDone: Boolean = false,
    val time: String,
    val label: String?="",
    val medicineCnt: Int,
)
