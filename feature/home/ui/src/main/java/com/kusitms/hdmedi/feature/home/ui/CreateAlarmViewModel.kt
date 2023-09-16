package com.kusitms.hdmedi.feature.home.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kusitms.hdmedi.feature.home.domain.HomeRepository
import com.kusitms.hdmedi.feature.home.ui.model.RangeDate
import com.kusitms.hdmedi.feature.home.ui.util.DateUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Date
import javax.inject.Inject


@HiltViewModel
class CreateAlarmViewModel @Inject constructor(
    repository: HomeRepository
) : ViewModel() {

    private val _selectedName = MutableLiveData<String>()
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

}