package com.kusitms.hdmedi.ui

import java.util.Calendar

interface CalendarListener {
    fun onFirstDateSelected(startDate: Calendar)
    fun onDateRangeSelected(startDate: Calendar, endDate: Calendar)
}