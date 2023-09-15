package com.kusitms.hdmedi.feature.medicine.domain.model

data class Medicine(
    val img: String,
    val name: String,
    val precautions: List<String>
)
