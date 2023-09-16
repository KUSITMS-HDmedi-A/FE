package com.kusitms.hdmedi.feature.home.ui

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.core.common.BaseFragment
import com.core.common.adapter.AlarmAdapter
import com.core.common.adapter.ProfileAdapter
import com.core.common.model.Profile
import com.kusitms.hdmedi.feature.home.ui.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels()

    override fun createView(binding: FragmentHomeBinding) {

    }

    override fun viewCreated() {
        binding.lifecycleOwner = this
        initView()
        setOnClickListener()
    }

    private fun initView() {
        binding.topBar.title = "홈"

        val profileList = profileList()
        binding.rvPeople.rv.adapter = ProfileAdapter(profileList) {clickedProfile ->
            Log.d(javaClass.name, "clicked : ${clickedProfile}")
            profileList.onEach {
                it.selected = it.name == clickedProfile.name
            }
        }

        // 약 알람
        binding.alarmCnt = 2
        binding.rvAlarm.adapter = AlarmAdapter(viewModel.alarmList) {
            //todo navigate
        }
    }

    private fun setOnClickListener() {
        binding.fabAlarm.setOnClickListener { navigateToCreateAlarm() }
    }

    private fun navigateToCreateAlarm() {
        Log.d(
            javaClass.name,
            "currentDestination : ${findNavController().currentDestination?.id == R.id.homeFragment}"
        )
        if (findNavController().currentDestination?.id == R.id.homeFragment)
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_homeFragment_to_createAlarmFragment)
    }

    private fun profileList(): MutableList<Profile> = mutableListOf(
        Profile(
            name = "나(엄마)",
            img = requireContext().getDrawable(com.core.common.R.drawable.img_mom)!!,
            selected = true
        ),
        Profile(
            name = "우리딸",
            img = requireContext().getDrawable(com.core.common.R.drawable.img_daughter)!!,
            selected = false
        ),
        Profile(
            name = "우리아들",
            img = requireContext().getDrawable(com.core.common.R.drawable.img_son)!!,
            selected = false
        ),
        Profile()
    )

}