package com.kusitms.hdmedi.feature.home.ui

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavGraph
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.core.common.BaseFragment
import com.core.common.adapter.AlarmAdapter
import com.core.common.adapter.ProfileAdapter
import com.core.common.model.Profile
import com.kusitms.hdmedi.feature.home.ui.adapter.WeekDateAdapter
import com.kusitms.hdmedi.feature.home.ui.databinding.FragmentHomeBinding
import com.kusitms.hdmedi.feature.home.ui.util.DateUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels()

    lateinit var alarmAdapter: AlarmAdapter

    override fun createView(binding: FragmentHomeBinding) {

    }

    override fun viewCreated() {
        binding.lifecycleOwner = this
        setAdapter()
        initView()
        setOnClickListener()
        bindingVm()
    }

    private fun bindingVm() {
        viewModel.selectedWeekDate.observe(viewLifecycleOwner) {
            Log.d(javaClass.name, "selectedDate changed : ${it.date}")
            viewModel.getAlarmList()
        }
        viewModel.selectedName.observe(viewLifecycleOwner) {
            Log.d(javaClass.name, "selectedName changed : ${it}")
            binding.name = it
            viewModel.getAlarmList()
        }

        viewModel.selectedAlarmList.observe(viewLifecycleOwner) {
            Log.d(javaClass.name, "selectedAlarmList changed : ${it}")
            if (::alarmAdapter.isInitialized) {
                alarmAdapter.updateList(it)
                binding.alarmCnt = it.size
            }
        }
    }

    private fun initView() {
        binding.topBar.title = "홈"

        val profileList = profileList()
        viewModel.updateSelectedName(profileList[0].name!!)
        binding.rvPeople.rv.adapter = ProfileAdapter(profileList) { clickedProfile ->
            Log.d(javaClass.name, "clicked : ${clickedProfile}")
            viewModel.updateSelectedName(clickedProfile.name!!)
        }

        // 달력
        binding.rvWeek.bringToFront()
        val gridLayoutManager = GridLayoutManager(context, 7)
        binding.rvWeek.layoutManager = gridLayoutManager
        binding.rvWeek.adapter = WeekDateAdapter(DateUtil.getCurrentWeek()) {
            viewModel.updateSelectedDate(it)
        }

        // 약 알람
        binding.rvAlarm.adapter = alarmAdapter
    }

    private fun setAdapter(){
        binding.alarmCnt = viewModel.alarmList.size
        alarmAdapter = AlarmAdapter(viewModel.alarmList) {
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
        findNavController().navigateSafe(HomeFragmentDirections.actionHomeFragmentToCreateAlarmFragment())

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
        Profile()
    )

    fun NavController.navigateSafe(directions: NavDirections) {
        // Get action by ID. If action doesn't exist on current node, return.
        val action = (currentDestination ?: graph).getAction(directions.actionId) ?: return
        var destId = action.destinationId
        val dest = graph.findNode(destId)
        if (dest is NavGraph) {
            // Action destination is a nested graph, which isn't a real destination.
            // The real destination is the start destination of that graph so resolve it.
            destId = dest.startDestinationId
        }
        if (currentDestination?.id != destId) {
            navigate(directions)
        }
    }

}