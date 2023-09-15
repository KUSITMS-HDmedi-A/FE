package com.kusitms.hdmedi.feature.home.ui

import android.util.Log
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.core.common.BaseFragment
import com.kusitms.hdmedi.feature.home.ui.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    override fun createView(binding: FragmentHomeBinding) {

    }

    override fun viewCreated() {
        binding.lifecycleOwner = this
        setOnClickListener()
    }

    private fun setOnClickListener(){
        binding.fabAlarm.setOnClickListener { navigateToCreateAlarm() }
    }
    private fun navigateToCreateAlarm(){
        Log.d(javaClass.name, "currentDestination : ${findNavController().currentDestination?.id == R.id.homeFragment}")
        if (findNavController().currentDestination?.id == R.id.homeFragment)
            Navigation.findNavController(binding.root).navigate(R.id.action_homeFragment_to_createAlarmFragment)
    }
}