package com.core.network.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ADHDQuestionResponse(
    val code: Int,
    val message: String,
    val questionsList: List<Question>,
    val character: List<String>?
)


data class Question(
    val blue: String,
    val description: String
)