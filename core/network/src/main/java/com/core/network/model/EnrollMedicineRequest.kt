package com.core.network.model


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EnrollMedicineRequest(
    val characterName: String,
    val endDate: String,
    val medicine: List<String>,
    val purpose: String,
    val startDate: String
)