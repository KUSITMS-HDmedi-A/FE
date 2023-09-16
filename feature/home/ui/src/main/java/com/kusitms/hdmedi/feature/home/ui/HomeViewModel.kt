package com.kusitms.hdmedi.feature.home.ui

import androidx.lifecycle.ViewModel
import com.kusitms.hdmedi.feature.home.domain.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    val homeRepository: HomeRepository
): ViewModel() {

    //val _selectedProfile

}