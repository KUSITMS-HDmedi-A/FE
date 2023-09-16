package com.kusitms.hdmedi.feature.home.ui.util

import android.util.Log
import com.kusitms.hdmedi.feature.home.ui.model.WeekDate
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

object DateUtil {

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    private val korDayFormat = SimpleDateFormat("EEE", Locale.KOREAN)
    private val engDayFormat = SimpleDateFormat("EEEE", Locale.ENGLISH)
    fun getCurrentWeek(): List<WeekDate> {
        val calendar: Calendar = Calendar.getInstance().apply {
            set(Calendar.MONTH, this.get(Calendar.MONTH))
            firstDayOfWeek = Calendar.SUNDAY
            set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)
        }
        val weekDates = mutableListOf<WeekDate>()
        repeat(7) {
            WeekDate(
                date = dateFormat.format(calendar.time),
                korDay = korDayFormat.format(calendar.time),
                engDay = engDayFormat.format(calendar.time)
            ).let {
                if (dateFormat.format(Calendar.getInstance().time) == it.date) it.isSelected = true
                weekDates.add(it)
            }
            calendar.add(Calendar.DATE, 1)
        }
        Log.d(javaClass.name, "${weekDates}")
        return weekDates.toList()
    }
}