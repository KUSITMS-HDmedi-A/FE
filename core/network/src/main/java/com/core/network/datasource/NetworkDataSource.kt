package com.core.network.datasource

import com.core.network.model.ADHDQuestionResponse
import com.core.network.model.EnrollMedicineRequest
import com.core.network.model.MedicineListResponse
import com.core.network.model.Response
import com.core.network.model.TokenResponse
import retrofit2.Call
import retrofit2.http.Header

interface NetworkDataSource {
    /**
     * adhd 진단
     */
    fun getAdhdQuestion(@Header("Authorization") token: String) : Call<ADHDQuestionResponse>

    /**
     * auth (회원)
     */
    suspend fun kakaoLogin(): TokenResponse

    /**
     * child
     */

    /**
     * medicine
     */
    suspend fun enrollMedicine(medicine: EnrollMedicineRequest) : Response
    suspend fun getMedicines(): MedicineListResponse
}