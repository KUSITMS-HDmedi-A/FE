package com.core.network.model

import com.squareup.moshi.Json

@Json
data class MedicineListResponse(
    val list: List<MedicinePeriodDto>
)

@Json
data class MedicinePeriodDto(
    val startDate: String,
    val endDate: String,
    val prescription: Boolean? = false,
    val purpose: String,
    val medicines: List<MedicineDto>
)

@Json
data class MedicineDto(
    val img: String,
    val name: String,
    val precautions: List<String>
)
