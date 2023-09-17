package com.core.network.model

import com.squareup.moshi.Json

@Json
data class MedicineListResponse(
    val characterList: List<Medicine>,
    val code: Int,
    val message: String
)

@Json
data class Medicine(
    val characterName: String,
    val enrollMedicineList: List<EnrolledMedicine>
)

@Json
data class EnrolledMedicine(
    val endDate: String,
    val medicineCount: Int,
    val medicineList: List<MedicineList>,
    val purpose: String,
    val startDate: String
)

@Json
data class MedicineList(
    val derections: String,
    val effectList: List<String>,
    val name: String
)
