package com.kusitms.hdmedi.feature.medicine.domain.model

data class MedicinePeriod(
    val startDate: String,
    val endDate: String,
    val prescription: Boolean? = false,
    val purpose: String,
    val medicines: List<Medicine>
)
