package com.kusitms.hdmedi.feature.home.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.core.common.BaseFragment
import com.core.common.adapter.ProfileAdapter
import com.core.common.model.Profile
import com.kusitms.hdmedi.feature.home.ui.databinding.FragmentCreateAlarmBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateAlarmFragment :
    BaseFragment<FragmentCreateAlarmBinding>(R.layout.fragment_create_alarm) {

    val viewModel: CreateAlarmViewModel by viewModels()

    override fun createView(binding: FragmentCreateAlarmBinding) {

    }

    override fun viewCreated() {
        binding.lifecycleOwner = this
        initView()
    }

    private fun initView() {
        // 헤더
        binding.header.apply {
            headerTitle = getString(R.string.add_alarm)
            rightTxt = getString(R.string.enroll)
            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }

        // 사람 선택
        val profileList = profileList()
        binding.rvPeople.rv.adapter = ProfileAdapter(profileList) { clickedProfile ->
            Log.d(javaClass.name, "clicked : ${clickedProfile}")
            viewModel.updateSelectedName(clickedProfile.name!!)
        }

        // 약 선택
        val itemList = listOf("타이레놀", "소화제", "김기약", "해열제")
        val itemAdapter: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), R.layout.item_spinner, itemList)
        binding.autoTv.setAdapter(itemAdapter)
        binding.autoTv.setOnItemClickListener { parent, view, position, id ->
            Log.d(javaClass.name, "${itemAdapter.getItemId(position)}")
        }

        // 기간 선택
        binding.layoutEnd.tvLabel.text = getString(R.string.end)

        // 요일 선택
        binding.btnEveryday.tvTitle.text = getString(R.string.everyday)
        binding.btnWeekday.tvTitle.text = getString(R.string.weekday)
        binding.btnWeekend.tvTitle.text = getString(R.string.weekend)
    }

    private fun profileList(): MutableList<Profile> = mutableListOf(
        Profile(
            name = "나(엄마)",
            img = requireContext().getDrawable(com.core.common.R.drawable.img_mom)!!,
            selected = true
        ),
        Profile(
            name = "김리아(딸)",
            img = requireContext().getDrawable(com.core.common.R.drawable.img_daughter)!!,
            selected = false
        ),
        Profile(
            name = "김리준(아들)",
            img = requireContext().getDrawable(com.core.common.R.drawable.img_son)!!,
            selected = false
        ),
    )

}