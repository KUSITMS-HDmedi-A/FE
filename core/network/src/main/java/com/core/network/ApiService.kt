package com.core.network

import com.core.network.model.ADHDQuestionResponse
import com.core.network.model.ADHDResultRequest
import com.core.network.model.ADHDResultResponse
import com.core.network.model.EnrollMedicineRequest
import com.core.network.model.MedicineListResponse
import com.core.network.model.Response
import com.core.network.model.TokenResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    /**
     * adhd 진단
     */
    @GET("/api/adhd/test")
    fun getAdhdQuestion(@Header("Authorization") token: String) : Call<ADHDQuestionResponse>

    @POST("/api/adhd/test/result")
    fun sendAdhdResult(@Header("Authorization") token: String, @Body result: ADHDResultRequest): Call<ADHDResultResponse>

    /**
     * auth (회원)
     */
    @POST("/api/user/auth/kakao-login")
    suspend fun kakaoLogin(): TokenResponse

    /**
     * child
     */

    /**
     * medicine
     */
    @POST("/api/child-medicine/enroll-medicine")
    fun enrollMedicine(@Header("Authorization") token: String, @Body medicine: EnrollMedicineRequest) : Call<Response>

    @GET("/api/child-medicine/medicine-manage")
    fun getMedicineManagement(@Header("Authorization") token: String): Call<MedicineListResponse>
}