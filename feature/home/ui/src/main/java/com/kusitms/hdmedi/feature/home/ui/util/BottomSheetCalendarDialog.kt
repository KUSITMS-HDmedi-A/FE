package com.kusitms.hdmedi.feature.home.ui.util

import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.archit.calendardaterangepicker.customviews.CalendarListener
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kusitms.hdmedi.feature.home.ui.databinding.FragmentBottomSheetCalendarBinding
import java.util.Date


class BottomSheetCalendarDialog(
    private val onClickRange: (Date, Date) -> Unit
) : BottomSheetDialogFragment() {

    lateinit var binding: FragmentBottomSheetCalendarBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBottomSheetCalendarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val calendarListener: CalendarListener = object : CalendarListener {
            override fun onDateRangeSelected(
                startDate: java.util.Calendar,
                endDate: java.util.Calendar
            ) {
                Log.d(javaClass.name, "Start Date:  ${startDate?.time}, End Date: ${endDate?.time}")
                if (startDate != null && endDate != null)
                    onClickRange(startDate.time, endDate.time)
            }

            override fun onFirstDateSelected(startDate: java.util.Calendar) {
                Log.d(javaClass.name, "Start Date:  ${startDate?.time}")
            }
        }
        binding.calendar.setCalendarListener(calendarListener)

    }

}

