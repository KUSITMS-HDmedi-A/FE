package com.kusitms.hdmedi.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.kusitms.hdmedi.ui.databinding.FragmentMedicineBinding

class MedicineFragment : Fragment() {

    lateinit var fragmentMedicineBinding: FragmentMedicineBinding

    lateinit var navController: NavController


    val fragmentList = mutableListOf<Fragment>()

    // 탭에 표시할 이름
    val tabName = arrayOf(
        "약 관리","복용 기록"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentMedicineBinding = FragmentMedicineBinding.inflate(inflater)

        fragmentMedicineBinding.run {
            topBarMedicine.mainHeaderTb.title = "약관리"

            fragmentList.clear()
            fragmentList.add(MedicineManagementFragment())
            fragmentList.add(MedicineManagementFragment())

            pagerTabMedicine.setUserInputEnabled(false)
            pagerTabMedicine.adapter = TabAdapterClass(this@MedicineFragment)

            // 탭 구성
            val tabLayoutMediator = TabLayoutMediator(tabMedicine, pagerTabMedicine){ tab: TabLayout.Tab, i: Int ->
                tab.text = tabName[i]
            }
            tabLayoutMediator.attach()

            }

        return fragmentMedicineBinding.root
    }

    // onViewCreated : 뷰를 그리고 나서 실행
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        fragmentMedicineBinding.run {
            // id클릭시 네비게이션 이동
            floatingActionButtonMedicineRegister.setOnClickListener {
                navController.navigate(com.kusitms.hdmedi.ui.R.id.action_medicineFragment_to_registerMedicinePurposeFragment)

            }
        }
    }

    override fun onResume() {
        super.onResume()
        fragmentList.clear()
        fragmentList.add(MedicineManagementFragment())
        fragmentList.add(MedicineManagementFragment())
        fragmentMedicineBinding.pagerTabMedicine.requestLayout()
    }

    // adapter 클래스
    inner class TabAdapterClass(fragmentActivity: MedicineFragment) : FragmentStateAdapter(fragmentActivity){
        override fun getItemCount(): Int {
            Log.d("##","fragmentList size : ${fragmentList.size}")
            return fragmentList.size
        }

        override fun createFragment(position: Int): Fragment {
            return fragmentList[position]
        }

    }
}