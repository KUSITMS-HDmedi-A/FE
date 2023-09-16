package com.core.network.datasource

import com.core.network.model.AddAlarmRequest
import com.core.network.model.CharacterData
import com.core.network.model.EnrollMedicineRequest
import com.core.network.model.FcmTokenRequest
import com.core.network.model.MedicineListResponse
import com.core.network.model.NotificationRequest
import com.core.network.model.Response
import com.core.network.model.TokenResponse
import com.core.network.model.idResponse
import retrofit2.http.Body

interface NetworkDataSource {

    /**
     * adhd 진단
     */

    /**
     * alarm
     */
    suspend fun postAlarm(alarmRequest: AddAlarmRequest): Response

    suspend fun getAddAlarmData(): List<CharacterData>

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

    /**
     * fcm
     */
    suspend fun sendNotification(@Body notificationRequest: NotificationRequest): idResponse

    suspend fun pathFCMToken(@Body fcmTokenRequest: FcmTokenRequest): idResponse
}