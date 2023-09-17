package com.kusitms.hdmedi.ui

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.kusitms.hdmedi.ui.databinding.FragmentMedicineBinding
import com.kusitms.hdmedi.ui.databinding.FragmentMedicineManagementBinding
import com.kusitms.hdmedi.ui.databinding.RowCharacterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MedicineFragment : Fragment() {

    private val medicineViewModel: MedicineViewModel by viewModels()

    lateinit var fragmentMedicineBinding: FragmentMedicineBinding

    lateinit var navController: NavController


    lateinit var viewModel: MedicineViewModel


    var handler: Handler = Handler()


    companion object {
        var characterName = ""
        var characterImage = com.core.common.R.drawable.img_daughter
        var onClickPosition = 0
        var characterImageList = listOf<Int>(
            com.core.common.R.drawable.img_mom,
            com.core.common.R.drawable.img_daughter,
            com.core.common.R.drawable.img_son,
        )
    }


    lateinit var historyFragment: HistoryFragment


    val fragmentList = mutableListOf<Fragment>()

    // 탭에 표시할 이름
    val tabName = arrayOf(
        "약 관리", "복용 기록"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentMedicineBinding = FragmentMedicineBinding.inflate(inflater)
        viewModel = ViewModelProvider(requireActivity())[MedicineViewModel::class.java]
        viewModel.run {
            characterMedicineList.observe(requireActivity()) {
                fragmentMedicineBinding.recyclerViewMedicinePeople.adapter?.notifyDataSetChanged()
            }
        }
        viewModel.getManageMedicine()

        fragmentMedicineBinding.run {
            topBarMedicine.mainHeaderTb.title = "약관리"


            historyFragment = HistoryFragment(viewModel = medicineViewModel)

            fragmentList.clear()
            fragmentList.add(MedicineManagementFragment())
            fragmentList.add(historyFragment)

            pagerTabMedicine.setUserInputEnabled(false)
            pagerTabMedicine.adapter = TabAdapterClass(this@MedicineFragment)

            // 탭 구성
            val tabLayoutMediator =
                TabLayoutMediator(tabMedicine, pagerTabMedicine) { tab: TabLayout.Tab, i: Int ->
                    tab.text = tabName[i]
                }
            tabLayoutMediator.attach()

            handler.postDelayed({
                recyclerViewMedicinePeople.run {
                    adapter = RecyclerViewAdapter()

                    layoutManager =
                        LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
                }
            }, 100)

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
        fragmentList.add(historyFragment)
        fragmentMedicineBinding.pagerTabMedicine.requestLayout()
        fragmentMedicineBinding.recyclerViewMedicinePeople.adapter?.notifyDataSetChanged()
    }

    inner class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolderClass>() {
        inner class ViewHolderClass(rowBinding: RowCharacterBinding) :
            RecyclerView.ViewHolder(rowBinding.root) {

            val rowName: TextView
            val rowImage: ImageView
            val rowLayout: ConstraintLayout


            init {
                rowName = rowBinding.textViewCharacterName
                rowImage = rowBinding.imageViewCharacter
                rowLayout = rowBinding.constraintLayoutCharacterCircle

                rowBinding.root.setOnClickListener {
                    onClickPosition = adapterPosition
                    fragmentMedicineBinding.pagerTabMedicine.requestLayout()
                    fragmentMedicineBinding.recyclerViewMedicinePeople.adapter?.notifyDataSetChanged()
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            val rowSelfCheckBinding = RowCharacterBinding.inflate(layoutInflater)
            val viewHolder = ViewHolderClass(rowSelfCheckBinding)

            rowSelfCheckBinding.root.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            return viewHolder
        }

        override fun getItemCount(): Int {
            return if (viewModel.characterMedicineList.value != null) viewModel.characterMedicineList.value!!.characterList.size
            else 0
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            holder.rowImage.setImageResource(characterImageList[position])
            holder.rowName.text =
                viewModel.characterMedicineList.value!!.characterList.get(position).characterName
            if (position == onClickPosition) {
                holder.rowLayout.setBackgroundResource(com.core.common.R.drawable.bg_circle_select)
                holder.rowName.setTextColor(resources.getColor(R.color.main_blue))
            } else {
                holder.rowLayout.setBackgroundResource(R.drawable.bg_circle_default)
                holder.rowName.setTextColor(resources.getColor(R.color.text_gray))
            }
        }
    }

    // adapter 클래스
    inner class TabAdapterClass(fragmentActivity: MedicineFragment) :
        FragmentStateAdapter(fragmentActivity) {
        override fun getItemCount(): Int {
//            Log.d("##","fragmentList size : ${fragmentList.size}")
            return fragmentList.size
        }

        override fun createFragment(position: Int): Fragment {
            return fragmentList[position]
        }

    }
}