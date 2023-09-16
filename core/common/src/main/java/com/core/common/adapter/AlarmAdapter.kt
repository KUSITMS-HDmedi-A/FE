package com.core.common.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.core.common.databinding.LayoutItemMedicineAlarmBinding
import com.core.common.model.Alarm

class AlarmAdapter(private val list: List<Alarm>, private val onClick: (Alarm) -> Unit) :
    RecyclerView.Adapter<AlarmAdapter.ItemViewHolder>() {
    inner class ItemViewHolder(private val binding: LayoutItemMedicineAlarmBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(alarmData: Alarm) {
            binding.apply {
                alarm = alarmData

                btnCheck.setOnClickListener {
                    alarmData.isDone = !alarmData.isDone
                }
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

}