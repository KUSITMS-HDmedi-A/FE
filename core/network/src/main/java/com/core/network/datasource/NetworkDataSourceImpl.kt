package com.core.network.datasource

import com.core.network.ApiService
import com.core.network.model.ADHDQuestionResponse
import com.core.network.model.EnrollMedicineRequest
import com.core.network.model.MedicineListResponse
import com.core.network.model.Response
import com.core.network.model.TokenResponse
import retrofit2.Call
import javax.inject.Inject

class NetworkDataSourceImpl @Inject constructor(
    private val networkApi: ApiService
) : NetworkDataSource {

    override fun getAdhdQuestion(token: String): Call<ADHDQuestionResponse> = networkApi.getAdhdQuestion(token = token)

    override suspend fun kakaoLogin(): TokenResponse = networkApi.kakaoLogin()


    override suspend fun enrollMedicine(medicine: EnrollMedicineRequest): Response =
        networkApi.enrollMedicine(medicine = medicine)

    override suspend fun getMedicines(): MedicineListResponse =
        networkApi.getMedicines()

}