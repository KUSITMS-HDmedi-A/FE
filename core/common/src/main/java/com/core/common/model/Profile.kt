package com.core.common.model

import android.graphics.drawable.Drawable

data class Profile(
    val name: String? = "",
    val img: Drawable? = null,
    var selected: Boolean = false,
)
