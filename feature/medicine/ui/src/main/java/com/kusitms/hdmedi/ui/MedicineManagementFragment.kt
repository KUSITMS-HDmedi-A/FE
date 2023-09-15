package com.kusitms.hdmedi.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.kusitms.hdmedi.ui.databinding.FragmentMedicineManagementBinding
import com.kusitms.hdmedi.ui.databinding.RowMedicineBinding
import com.kusitms.hdmedi.ui.databinding.RowMedicineDetailBinding
import com.kusitms.hdmedi.ui.databinding.RowMedicineTagBinding

class MedicineManagementFragment : Fragment() {

    lateinit var fragmentMedicineManagementBinding: FragmentMedicineManagementBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentMedicineManagementBinding = FragmentMedicineManagementBinding.inflate(inflater)

        fragmentMedicineManagementBinding.run {

            linearLayoutMedicineManagement.visibility = View.GONE

            recyclerViewMedicineManagement.run {
                adapter = RecyclerViewAdapter()

                layoutManager = LinearLayoutManager(requireContext())
            }
        }
        return fragmentMedicineManagementBinding.root
    }

    inner class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolderClass>() {
        inner class ViewHolderClass(rowBinding: RowMedicineBinding) :
            RecyclerView.ViewHolder(rowBinding.root) {

            val medicineDate: TextView
            val buttonPrescription: Button
            val medicineType: TextView
            val medicineCount: TextView
            val buttonmedicineExpand: Button
            val recyclerViewMedicine: RecyclerView


            init {
                medicineDate = rowBinding.textViewMedicineDate
                buttonPrescription = rowBinding.buttonMedicinePrescription
                medicineType = rowBinding.textViewMedicineType
                medicineCount = rowBinding.textViewMedicineCount
                buttonmedicineExpand = rowBinding.buttonMedicineExpand
                recyclerViewMedicine = rowBinding.recyclerViewMedicine

                recyclerViewMedicine.run {
                    visibility = View.GONE
                    adapter = DetailRecyclerViewAdapter()

                    layoutManager = LinearLayoutManager(requireContext())
                }

                buttonmedicineExpand.setOnClickListener {
                    if(recyclerViewMedicine.visibility == View.VISIBLE) {
                        recyclerViewMedicine.visibility = View.GONE
                        buttonmedicineExpand.run {
                            text = "약 펼쳐보기"
                            setIconResource(R.drawable.arrow_down)
//                            setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.arrow_down, 0)
                        }
                    } else {
                        recyclerViewMedicine.visibility = View.VISIBLE
                        buttonmedicineExpand.run {
                            text = "약 간단히 보기"
                            setIconResource(R.drawable.arrow_up)
//                            setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_up, 0)
                        }
                    }
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            val rowPostListBinding = RowMedicineBinding.inflate(layoutInflater)
            val viewHolder = ViewHolderClass(rowPostListBinding)

            rowPostListBinding.root.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            return viewHolder
        }

        override fun getItemCount(): Int {
            return 3
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {

        }
    }

    inner class DetailRecyclerViewAdapter : RecyclerView.Adapter<DetailRecyclerViewAdapter.DetailViewHolderClass>() {
        inner class DetailViewHolderClass(rowBinding: RowMedicineDetailBinding) :
            RecyclerView.ViewHolder(rowBinding.root) {

            val medicineName: TextView
            val medicineType: TextView
            val medicineTag: RecyclerView
            val medicineInfo: TextView


            init {
                medicineName = rowBinding.textViewMedicineDetailName
                medicineType = rowBinding.textViewMedicineDetailType
                medicineInfo = rowBinding.textViewMedicineDetailInfo
                medicineTag = rowBinding.recyclerViewMedicineDetailTag

                medicineTag.run {
                    adapter = TagRecyclerViewAdapter()

                    layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolderClass {
            val rowMedicineListBinding = RowMedicineDetailBinding.inflate(layoutInflater)
            val viewHolder = DetailViewHolderClass(rowMedicineListBinding)

            rowMedicineListBinding.root.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            return viewHolder
        }

        override fun getItemCount(): Int {
            return 3
        }

        override fun onBindViewHolder(holder: DetailViewHolderClass, position: Int) {

        }
    }

    inner class TagRecyclerViewAdapter : RecyclerView.Adapter<TagRecyclerViewAdapter.TagViewHolderClass>() {
        inner class TagViewHolderClass(rowBinding: RowMedicineTagBinding) :
            RecyclerView.ViewHolder(rowBinding.root) {

            val medicineTag: TextView


            init {
                medicineTag = rowBinding.textViewTag
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolderClass {
            val rowMedicineTagBinding = RowMedicineTagBinding.inflate(layoutInflater)
            val viewHolder = TagViewHolderClass(rowMedicineTagBinding)

            rowMedicineTagBinding.root.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            return viewHolder
        }

        override fun getItemCount(): Int {
            return 3
        }

        override fun onBindViewHolder(holder: TagViewHolderClass, position: Int) {

        }
    }
}