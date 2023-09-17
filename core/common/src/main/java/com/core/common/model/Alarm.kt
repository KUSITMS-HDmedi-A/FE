package com.core.common.model

data class Alarm(
    var isDone: Boolean = false,
    val time: String,
    val label: String?="",
    val medicineCnt: Int,
): Comparable<Alarm> {
    override fun compareTo(other: Alarm): Int = when {
        time < other.time -> 1
        medicineCnt < other.medicineCnt -> 1
        else -> 1
    }
}
