package com.core.network

import com.core.network.model.EnrollMedicineRequest
import com.core.network.model.MedicineListResponse
import com.core.network.model.Response
import com.core.network.model.TokenResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    /**
     * adhd 진단
     */

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
    @POST("/api/medicine/enroll-medicine")
    suspend fun enrollMedicine(@Body medicine: EnrollMedicineRequest) : Response

    //todo 임의로 한 것 (진짜 아님)
    @GET("")
    suspend fun getMedicines(): MedicineListResponse
}