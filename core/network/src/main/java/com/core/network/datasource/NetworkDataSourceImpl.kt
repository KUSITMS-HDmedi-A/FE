package com.core.network.datasource

import com.core.network.ApiService
import com.core.network.model.EnrollMedicineRequest
import com.core.network.model.MedicineListResponse
import com.core.network.model.Response
import com.core.network.model.TokenResponse
import javax.inject.Inject

class NetworkDataSourceImpl @Inject constructor(
    private val networkApi: ApiService
) : NetworkDataSource {
    override suspend fun kakaoLogin(): TokenResponse = networkApi.kakaoLogin()


    override suspend fun enrollMedicine(medicine: EnrollMedicineRequest): Response =
        networkApi.enrollMedicine(medicine = medicine)

    override suspend fun getMedicines(): MedicineListResponse =
        networkApi.getMedicines()

}