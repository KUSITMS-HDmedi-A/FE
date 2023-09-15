package com.core.network.datasource

import com.core.network.model.EnrollMedicineRequest
import com.core.network.model.MedicineListResponse
import com.core.network.model.Response
import com.core.network.model.TokenResponse

interface NetworkDataSource {
    /**
     * adhd 진단
     */

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