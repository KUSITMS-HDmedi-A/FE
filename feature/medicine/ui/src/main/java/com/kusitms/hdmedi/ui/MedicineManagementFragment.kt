package com.kusitms.hdmedi.ui

import android.media.Image
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.core.network.model.Medicine
import com.core.network.model.MedicineListResponse
import com.kusitms.hdmedi.ui.MedicineFragment.Companion.onClickPosition
import com.kusitms.hdmedi.ui.databinding.FragmentMedicineManagementBinding
import com.kusitms.hdmedi.ui.databinding.RowMedicineBinding
import com.kusitms.hdmedi.ui.databinding.RowMedicineDetailBinding
import com.kusitms.hdmedi.ui.databinding.RowMedicineTagBinding

class MedicineManagementFragment : Fragment() {

    lateinit var fragmentMedicineManagementBinding: FragmentMedicineManagementBinding

    lateinit var viewModel: MedicineViewModel

    var expandPosition = 0
    var tagPosition = 0

    var handler: Handler = Handler()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentMedicineManagementBinding = FragmentMedicineManagementBinding.inflate(inflater)

        viewModel = ViewModelProvider(requireActivity())[MedicineViewModel::class.java]
        viewModel.run {
            characterMedicineList.observe(requireActivity()) {
                fragmentMedicineManagementBinding.recyclerViewMedicineManagement.adapter?.notifyDataSetChanged()
            }
        }
        viewModel.getManageMedicine()

        fragmentMedicineManagementBinding.run {

            linearLayoutMedicineManagement.visibility = View.GONE

            handler.postDelayed({
                recyclerViewMedicineManagement.run {
                    adapter = RecyclerViewAdapter()

                    layoutManager = LinearLayoutManager(requireContext())

                }
                recyclerViewMedicineManagement.adapter?.notifyDataSetChanged()
            }, 100)

        }
        return fragmentMedicineManagementBinding.root
    }

    override fun onResume() {
        super.onResume()
        fragmentMedicineManagementBinding.recyclerViewMedicineManagement.adapter?.notifyDataSetChanged()
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
                        expandPosition = adapterPosition
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
            return viewModel.characterMedicineList.value?.characterList!!.get(0).enrollMedicineList.size
        }


        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            holder.medicineCount.text = "${viewModel.characterMedicineList.value?.characterList!!.get(onClickPosition).enrollMedicineList.get(position)?.medicineCount.toString()}알"
            holder.medicineType.text = viewModel.characterMedicineList.value?.characterList!!.get(onClickPosition).enrollMedicineList.get(position).purpose.toString()
            holder.medicineDate.text = "${viewModel.characterMedicineList.value?.characterList!!.get(onClickPosition).enrollMedicineList.get(position).startDate} ~ ${viewModel.characterMedicineList.value?.characterList!!.get(onClickPosition).enrollMedicineList.get(position).endDate}"
        }
    }

    inner class DetailRecyclerViewAdapter : RecyclerView.Adapter<DetailRecyclerViewAdapter.DetailViewHolderClass>() {
        inner class DetailViewHolderClass(rowBinding: RowMedicineDetailBinding) :
            RecyclerView.ViewHolder(rowBinding.root) {

            val medicineName: TextView
            val medicineTag: RecyclerView
            val medicineInfo: TextView
            val medicineImage: ImageView


            init {
                medicineName = rowBinding.textViewMedicineDetailName
                medicineInfo = rowBinding.textViewMedicineDetailInfo
                medicineTag = rowBinding.recyclerViewMedicineDetailTag
                medicineImage = rowBinding.imageViewMedicineDetail

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
            return viewModel.characterMedicineList.value?.characterList!!.get(onClickPosition).enrollMedicineList.get(expandPosition).medicineCount
        }

        override fun onBindViewHolder(holder: DetailViewHolderClass, position: Int) {
            holder.medicineName.text = viewModel.characterMedicineList.value?.characterList!!.get(onClickPosition).enrollMedicineList.get(expandPosition).medicineList.get(position).name
            holder.medicineInfo.text = viewModel.characterMedicineList.value?.characterList!!.get(onClickPosition).enrollMedicineList.get(expandPosition).medicineList.get(position).derections
            tagPosition = position
            holder.medicineImage.setImageResource(R.drawable.medicine)
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
            return viewModel.characterMedicineList.value?.characterList!!.get(onClickPosition).enrollMedicineList.get(expandPosition).medicineList.get(tagPosition).effectList.size
        }

        override fun onBindViewHolder(holder: TagViewHolderClass, position: Int) {
            holder.medicineTag.text = viewModel.characterMedicineList.value?.characterList!!.get(onClickPosition).enrollMedicineList.get(expandPosition).medicineList.get(tagPosition).effectList.get(position)
        }
    }
}