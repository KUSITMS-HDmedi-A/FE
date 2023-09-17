package com.core.network.model

data class ADHDResultResponse(
    val code: Int,
    val message: String,
    val result: String,
    val score: Int
)
