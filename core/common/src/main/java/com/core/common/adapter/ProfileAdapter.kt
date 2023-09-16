package com.core.common.adapter

import android.content.res.ColorStateList
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
                binding.apply {
                    if (profile.img != null) {
                        Glide.with(binding.root)
                            .load(profile.img)
                            .into(binding.ivProfile)
                        name = profile.name
                    }
                    else clCircle.background = binding.root.resources.getDrawable(R.drawable.bg_circle_stroke)
                }
                binding.root.setOnClickListener {
                    onClick(profile)
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemViewHolder(ItemProfileBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(list[position])
    }
}
