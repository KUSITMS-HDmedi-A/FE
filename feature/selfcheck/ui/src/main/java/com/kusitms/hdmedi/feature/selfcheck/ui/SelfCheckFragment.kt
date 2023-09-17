package com.kusitms.hdmedi.feature.selfcheck.ui

import android.media.Image
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.core.common.databinding.ItemProfileBinding
import com.kusitms.hdmedi.feature.selfcheck.ui.databinding.FragmentSelfCheckBinding
import com.kusitms.hdmedi.feature.selfcheck.ui.databinding.RowSelfcheckListBinding

class SelfCheckFragment : Fragment() {

    lateinit var fragmentSelfCheckBinding: FragmentSelfCheckBinding

    lateinit var navController: NavController

    val imageList = listOf<Int>(
        R.drawable.check_list1,
        R.drawable.check_list2,
        R.drawable.check_list3,
        R.drawable.check_list4,
        R.drawable.check_list5,
        R.drawable.check_list6,
        R.drawable.check_list7,
        R.drawable.check_list8,
        R.drawable.check_list9,
        R.drawable.check_list10
    )

    val nameList = listOf<String>(
        "ADHD",
        "편두통",
        "위 기능",
        "조울증",
        "불면증",
        "허리디스크",
        "하지정맥류",
        "카페인중독",
        "번아웃",
        "냉방병"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentSelfCheckBinding = FragmentSelfCheckBinding.inflate(inflater)

        fragmentSelfCheckBinding.run {

            recyclerViewSelfCheck.run {
                adapter = RecyclerViewAdapter()

                layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            }

            topBarSelfCheck.mainHeaderTb.title = "자가진단"
        }
        return fragmentSelfCheckBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        fragmentSelfCheckBinding.run {
            buttonSelfCheckStart.setOnClickListener {
                navController.navigate(R.id.action_selfCheckFragment_to_adhdFragment)
            }
        }
    }

    inner class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolderClass>() {
        inner class ViewHolderClass(rowBinding: RowSelfcheckListBinding) :
            RecyclerView.ViewHolder(rowBinding.root) {

            val rowName: TextView
            val rowImage: ImageView
            val rowLayout: ConstraintLayout


            init {
                rowName = rowBinding.textViewName
                rowImage = rowBinding.imageViewRowSelfCheck
                rowLayout = rowBinding.constraintLayoutCircle
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            val rowSelfCheckBinding = RowSelfcheckListBinding.inflate(layoutInflater)
            val viewHolder = ViewHolderClass(rowSelfCheckBinding)

            rowSelfCheckBinding.root.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            return viewHolder
        }

        override fun getItemCount(): Int {
            return 10
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            holder.rowImage.setImageResource(imageList[position])
            holder.rowName.text = nameList[position].toString()
            if(position == 0) {
                holder.rowLayout.setBackgroundResource(com.core.common.R.drawable.bg_circle_select)
                holder.rowName.setTextColor(resources.getColor(R.color.main_blue))
            } else {
                holder.rowLayout.setBackgroundResource(R.drawable.bg_circle_default)
            }
        }
    }
}