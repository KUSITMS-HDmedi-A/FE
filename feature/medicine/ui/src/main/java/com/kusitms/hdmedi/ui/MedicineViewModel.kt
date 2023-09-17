package com.kusitms.hdmedi.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.model.Alarm
import com.core.common.model.WeekDate
import com.kusitms.hdmedi.feature.medicine.data.repo.MedicineRepositoryImpl
import com.kusitms.hdmedi.feature.medicine.domain.repo.MedicineRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MedicineViewModel @Inject constructor(
    val repository: MedicineRepositoryImpl
): ViewModel() {


    private val _selectedDate = MutableLiveData<String>("")
    val selectedDate get() = _selectedDate

    fun updateSelectedDate(date: String) {
        _selectedDate.value = date
    }

    fun getHistoryList(){
        viewModelScope.launch {

        }
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