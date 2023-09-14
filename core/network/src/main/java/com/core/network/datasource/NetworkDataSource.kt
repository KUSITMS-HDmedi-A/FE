package com.core.network.datasource

import com.core.network.model.EnrollMedicineRequestDto
import com.core.network.model.ResponseDto
import com.core.network.model.TokenResponseDto

interface NetworkDataSource {
    /**
     * adhd 진단
     */

    /**
     * auth (회원)
     */
    suspend fun kakaoLogin(): TokenResponseDto

    /**
     * child
     */

    /**
     * medicine
     */
    suspend fun enrollMedicine(medicine: EnrollMedicineRequestDto) : ResponseDto
}