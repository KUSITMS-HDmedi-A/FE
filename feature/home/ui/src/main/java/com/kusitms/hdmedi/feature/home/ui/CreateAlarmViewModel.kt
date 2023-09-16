package com.kusitms.hdmedi.feature.home.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kusitms.hdmedi.feature.home.domain.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class CreateAlarmViewModel @Inject constructor(
    repository: HomeRepository
): ViewModel() {

    private val _selectedName = MutableLiveData<String>()
    val selectedName get() = _selectedName

    fun updateSelectedName(name: String){
        _selectedName.value = name
    }

}