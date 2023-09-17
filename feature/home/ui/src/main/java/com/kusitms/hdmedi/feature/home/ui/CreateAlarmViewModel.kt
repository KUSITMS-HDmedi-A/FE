package com.kusitms.hdmedi.feature.home.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kusitms.hdmedi.feature.home.domain.HomeRepository
import com.kusitms.hdmedi.feature.home.domain.model.AddAlarmInfo
import com.kusitms.hdmedi.feature.home.ui.model.RangeDate
import com.core.common.DateUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject


@HiltViewModel
class CreateAlarmViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {

    private val _selectedName = MutableLiveData<String>("")
    val selectedName get() = _selectedName

    private var _rangeDate = MutableLiveData<RangeDate?>(null)
    val rangeDate get() = _rangeDate

    fun updateSelectedName(name: String) {
        _selectedName.value = name
    }

    fun updateRangeDate(st: Date, en: Date) {
        _rangeDate.value = RangeDate(
            startDate = DateUtil.toDateStringFormat(st),
            startDateUI = DateUtil.toMonthDayStringFormat(st),
            endDate = DateUtil.toDateStringFormat(en),
            endDateUI = DateUtil.toMonthDayStringFormat(en)
        )
    }

    fun onClickEnrollBtn(medicine: String, hour: Int, minute: Int, label: String? = "") {
        val daysOfWeek =
            listOf("monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday")
        val time = String.format("%02d:%02d:00", hour, minute)

        viewModelScope.launch {
            repository.postAlarm(
                AddAlarmInfo(
                    character = selectedName.value!!,
                    medicine = medicine,
                    day = daysOfWeek,
                    startDate = rangeDate.value!!.startDate,
                    endDate = rangeDate.value!!.endDate,
                    time = time,
                    label = label
                )
            ).collect{
                Log.d(javaClass.name, "reponse: ${it.code}")
            }
        }
    }

}