package com.core.network.datasource

import com.core.network.ApiService
import com.core.network.model.EnrollMedicineRequestDto
import com.core.network.model.ResponseDto
import com.core.network.model.TokenResponseDto
import javax.inject.Inject

class NetworkDataSourceImpl @Inject constructor(
    private val networkApi: ApiService
) : NetworkDataSource {
    override suspend fun kakaoLogin(): TokenResponseDto = networkApi.kakaoLogin()


    override suspend fun enrollMedicine(medicine: EnrollMedicineRequestDto): ResponseDto =
        networkApi.enrollMedicine(medicine = medicine)
}