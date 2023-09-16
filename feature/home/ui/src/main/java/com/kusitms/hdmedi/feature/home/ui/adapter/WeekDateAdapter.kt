package com.kusitms.hdmedi.feature.home.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kusitms.hdmedi.feature.home.ui.databinding.ItemWeekdateBinding
import com.kusitms.hdmedi.feature.home.ui.model.WeekDate

class WeekDateAdapter(
    private val list: List<WeekDate>,
    private val onClickDate: (WeekDate) -> Unit
): RecyclerView.Adapter<WeekDateAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemWeekdateBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(date: WeekDate) {
            binding.weekDate = date
            val parts = date.date.split("-")
            val dayOfMonth = parts.getOrNull(2)
            binding.tvNumDay.text = dayOfMonth
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemWeekdateBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }
}