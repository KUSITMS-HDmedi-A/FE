package com.core.network.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NotificationRequest(
    val body: String,
    val targetUserId: Int,
    val title: String
)
