package com.core.network

import com.core.network.model.EnrollMedicineRequestDto
import com.core.network.model.ResponseDto
import com.core.network.model.TokenResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    /**
     * adhd 진단
     */

    /**
     * auth (회원)
     */
    @POST("/api/user/auth/kakao-login")
    suspend fun kakaoLogin(): TokenResponseDto

    /**
     * child
     */

    /**
     * medicine
     */
    @POST("/api/medicine/enroll-medicine")
    suspend fun enrollMedicine(@Body medicine: EnrollMedicineRequestDto) : ResponseDto
}