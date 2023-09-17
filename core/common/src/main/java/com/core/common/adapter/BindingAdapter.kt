package com.core.common.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.core.common.R

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("showImageProfile")
    fun ImageView.showImageProfile(name: String?){
        var drawable: Int = if (name == null) R.drawable.img_mom
        else if (name.contains("엄마")) R.drawable.img_mom
        else if (name.contains("딸")) R.drawable.img_daughter
        else if (name.contains("아들")) R.drawable.img_son
        else R.drawable.img_son

        Glide.with(this.context)
            .load(drawable)
            .fitCenter()
            .into(this)
    }
}