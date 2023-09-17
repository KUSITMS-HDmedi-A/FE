package com.kusitms.hdmedi.feature.home.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.core.common.model.Alarm
import com.kusitms.hdmedi.feature.home.domain.HomeRepository
import com.core.common.model.WeekDate
import com.core.common.DateUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    val homeRepository: HomeRepository
) : ViewModel() {

    private val _selectedName = MutableLiveData<String>()
    val selectedName get() = _selectedName

    private val _selectedAlarmList = MutableLiveData<List<Alarm>>()
    val selectedAlarmList get() = _selectedAlarmList

    private val _selectedWeekDate = MutableLiveData<WeekDate>()
    val selectedWeekDate get() = _selectedWeekDate

    init {
        _selectedWeekDate.value = DateUtil.getTodayWeekDate()
    }

    fun updateSelectedName(name: String){
        _selectedName.value = name
    }

    fun updateSelectedDate(weekDate: WeekDate) {
        _selectedWeekDate.value = weekDate
    }

    fun getAlarmList() {

        if (_selectedWeekDate.value == null || _selectedName.value == null) return

        val weekDate = selectedWeekDate.value!!
        val name = selectedName.value!!
//        viewModelScope.launch {
//            homeRepository.getAlarmRecodes(weekDate.date).collect {
//                Log.d(javaClass.name, "${it}")
//                val selected = it.filter { profileAlarmList ->
//                    profileAlarmList.name == name
//                }
//                _selectedAlarmList.value = if (selected.isNotEmpty()) selected[0].alarmList.sorted() else listOf()
//            }
//        }
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