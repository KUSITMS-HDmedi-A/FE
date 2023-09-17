package com.core.common.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.core.common.databinding.LayoutItemMedicineAlarmBinding
import com.core.common.model.Alarm
import com.google.android.material.snackbar.Snackbar

class AlarmAdapter(private var list: List<Alarm>, private val onClick: (Alarm) -> Unit) :
    RecyclerView.Adapter<AlarmAdapter.ItemViewHolder>() {
    inner class ItemViewHolder(private val binding: LayoutItemMedicineAlarmBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(alarmData: Alarm) {
            binding.apply {
                alarm = alarmData

                btnCheck.setOnClickListener {
                    val idx = list.indexOf(alarmData)
                    alarmData.isDone = !alarmData.isDone
                    notifyItemChanged(idx)
                    val percent = list.filter { it.isDone }.size / list.size * 100
                    Snackbar.make(binding.root.context, binding.root, "💊오늘 목표 ${percent}%를 달성했어요.", Snackbar.LENGTH_SHORT).show()
                }

                root.setOnClickListener { onClick }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder = ItemViewHolder(
        LayoutItemMedicineAlarmBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun updateList(alarmList: List<Alarm>) {
        list = alarmList
        notifyDataSetChanged()
    }

}