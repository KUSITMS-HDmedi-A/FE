package com.core.network.datasource

import com.core.network.model.AddAlarmRequest
import com.core.network.model.AlarmAddResponse
import com.core.network.model.Character
import com.core.network.model.DoseRecordResponse
import com.core.network.model.EnrollMedicineRequest
import com.core.network.model.FcmTokenRequest
import com.core.network.model.MedicineListResponse
import com.core.network.model.NotificationRequest
import com.core.network.model.Response
import com.core.network.model.TokenResponse
import com.core.network.model.idResponse
import retrofit2.http.Body
import retrofit2.http.Path

interface NetworkDataSource {

    /**
     * adhd 진단
     */

    /**
     * alarm
     */
    suspend fun postAlarm(alarmRequest: AddAlarmRequest): Response

    suspend fun getAddAlarmData(): AlarmAddResponse

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
     * child-medicine
     */
    suspend fun getDoseRecords(todayDate: String): List<Character>

    /**
     * fcm
     */
    suspend fun sendNotification(notificationRequest: NotificationRequest): idResponse

    suspend fun pathFCMToken(fcmTokenRequest: FcmTokenRequest): idResponse
}