package com.kusitms.hdmedi.feature.home.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.core.common.BaseFragment
import com.kusitms.hdmedi.feature.home.ui.databinding.FragmentCreateAlarmBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateAlarmFragment :
    BaseFragment<FragmentCreateAlarmBinding>(R.layout.fragment_create_alarm) {
    override fun createView(binding: FragmentCreateAlarmBinding) {

    }

    override fun viewCreated() {
        binding.lifecycleOwner = this
        initView()
    }

    private fun initView() {
        binding.header.apply {
            headerTitle = getString(R.string.add_alarm)
            rightTxt = getString(R.string.enroll)
        }

        val itemList = listOf("타이레놀", "소화제", "김기약", "해열제")
        val itemAdapter: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), R.layout.item_spinner, itemList)
        binding.autoTv.setAdapter(itemAdapter)
        binding.autoTv.setOnItemClickListener { parent, view, position, id ->
            Log.d(javaClass.name, "${itemAdapter.getItemId(position)}")
        }

        binding.layoutEnd.tvLabel.text = getString(R.string.end)
    }

}