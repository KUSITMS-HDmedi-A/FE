package com.kusitms.hdmedi.feature.home.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.core.common.model.Alarm
import com.kusitms.hdmedi.feature.home.domain.HomeRepository
import com.kusitms.hdmedi.feature.home.ui.model.WeekDate
import com.kusitms.hdmedi.feature.home.ui.util.DateUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    val homeRepository: HomeRepository
): ViewModel() {

    //val _selectedProfile

    private val _selectedWeekDate = MutableLiveData<WeekDate>()
    val selectedWeekDate get() = _selectedWeekDate

    init {
        _selectedWeekDate.value = DateUtil.getTodayWeekDate()
    }

    fun updateSelectedDate(weekDate: WeekDate){
        _selectedWeekDate.value = weekDate
    }


    val alarmList = listOf<Alarm>(
        Alarm(
            isDone = true,
            time = "9:00",
            label = "빈속에 먹으면 안됨. 꼭 아침 먹이고!",
            medicineCnt = 1,
        ),
        Alarm(
            isDone = false,
            time = "12:30",
            label = "빈속에 먹으면 안됨. 꼭 아침 먹이고!",
            medicineCnt = 4,
        ),
        Alarm(
            isDone = false,
            time = "22:00",
            label = "빈속에 먹으면 안됨. 꼭 아침 먹이고!",
            medicineCnt = 2,
        )
    )

}