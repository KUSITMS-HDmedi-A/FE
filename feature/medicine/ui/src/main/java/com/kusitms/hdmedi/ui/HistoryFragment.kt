package com.kusitms.hdmedi.ui

import com.core.common.BaseFragment
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
    }

    private fun initView(){
        // 달력
        //Log.d(javaClass.name, "${binding.calendar.startDate} - ${binding.calendar.endDate}")
        val startSelectionDate: Calendar = Calendar.getInstance()
        binding.calendar.setCurrentMonth(startSelectionDate)

        // 약
        binding.rvHistory.adapter = historyAdapter
        binding.alarmCnt = viewModel.alarmList.size
        binding.name = "엄마(나)"
    }

    private fun setAdapter(){
        historyAdapter = AlarmAdapter(viewModel.alarmList) {
            //todo navigate
        }
    }
}