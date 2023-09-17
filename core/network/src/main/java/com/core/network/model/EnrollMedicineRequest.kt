package com.core.network.model


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EnrollMedicineRequest(
    val purpose: String,
    val startDate: String,
    val endDate: String,
    val characterName: String,
    val medicine: List<String>
)