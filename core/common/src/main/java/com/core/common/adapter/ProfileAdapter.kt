package com.core.common.adapter

import android.content.res.ColorStateList
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.core.common.R
import com.core.common.databinding.ItemProfileBinding
import com.core.common.model.Profile

class ProfileAdapter(
    private val list: List<Profile>,
    private val onClick: (Profile) -> Unit
) : RecyclerView.Adapter<ProfileAdapter.ItemViewHolder>() {
    inner class ItemViewHolder(private val binding: ItemProfileBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(profile: Profile) {
            if (profile.name == "") {
                Log.d(javaClass.name, "profile = $profile")
                binding.clCircle.background =
                    binding.root.resources.getDrawable(R.drawable.bg_circle_stroke)
            } else {
                binding.profile = profile
                Glide.with(binding.root)
                    .load(profile.img)
                    .into(binding.ivProfile)
                binding.clCircle.background = if (profile.selected)
                    binding.root.resources.getDrawable(R.drawable.bg_circle_select)
                else
                    binding.root.resources.getDrawable(R.drawable.bg_circle_default)
            }

            binding.root.setOnClickListener {
                updateSelected(profile)
                onClick(profile)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemViewHolder(
            ItemProfileBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(list[position])
    }

    private fun updateSelected(date: Profile) {
        val index = mutableListOf<Int>()
        for (i in list.indices) {
            if (list[i].selected && list[i] != date) {
                index.add(i)
                list[i].selected = false
            } else if (list[i] == date) {
                index.add(i)
                list[i].selected = true
            }
        }
        index.forEach { notifyItemChanged(it) }
    }
}
