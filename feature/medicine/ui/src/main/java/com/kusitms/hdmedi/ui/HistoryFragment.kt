package com.kusitms.hdmedi.ui

import android.text.format.DateUtils
import android.util.Log
import android.view.View
import com.archit.calendardaterangepicker.customviews.CalendarListener
import com.core.common.BaseFragment
import com.core.common.DateUtil
import com.core.common.adapter.AlarmAdapter
import com.kusitms.hdmedi.ui.databinding.FragmentHistoryBinding
import java.util.Calendar


class HistoryFragment(
    private val viewModel: MedicineViewModel
): BaseFragment<FragmentHistoryBinding>(R.layout.fragment_history) {

    lateinit var historyAdapter: AlarmAdapter
    override fun createView(binding: FragmentHistoryBinding) {

    }

    override fun viewCreated() {
        binding.lifecycleOwner = this
        setAdapter()
        initView()
        bindingVm()
    }

    private fun bindingVm() {
        viewModel.selectedDate.observe(viewLifecycleOwner) {
            Log.d(javaClass.name, "selectedDate changed : ${it}")
            if (it != "") viewModel.getHistoryList()
        }
    }

    private fun initView(){
        // 달력
        Log.d(javaClass.name, "${binding.calendar.startDate} - ${binding.calendar.endDate?.time}")
        val startSelectionDate: Calendar = Calendar.getInstance()
        binding.calendar.setCurrentMonth(startSelectionDate)
        binding.calendar.setCalendarListener(object : CalendarListener {
            override fun onDateRangeSelected(startDate: Calendar, endDate: Calendar) {
                val stDate = binding.calendar.startDate!!.time
                viewModel.updateSelectedDate(DateUtil.toDateStringFormat(stDate))
                historyAdapter.updateList(viewModel.alarmList)
                binding.alarmCnt = historyAdapter.itemCount
                historyAdapter.notifyDataSetChanged()
                binding.llEmpty.visibility = View.GONE
                Log.d(javaClass.name, "djfkdjkfs")
            }

            override fun onFirstDateSelected(startDate: Calendar) {

            }

        })

        // 약
        binding.rvHistory.adapter = historyAdapter
        binding.alarmCnt = historyAdapter.itemCount
        binding.name = "엄마(나)"
    }

    private fun setAdapter(){
        historyAdapter = AlarmAdapter(listOf()) {
            //todo navigate
        }
    }
}