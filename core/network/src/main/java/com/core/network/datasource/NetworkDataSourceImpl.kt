package com.core.network.datasource

import com.core.network.ApiService
import com.core.network.model.AddAlarmRequest
import com.core.network.model.AlarmAddResponse
import com.core.network.model.Character
import com.core.network.model.EnrollMedicineRequest
import com.core.network.model.FcmTokenRequest
import com.core.network.model.MedicineListResponse
import com.core.network.model.NotificationRequest
import com.core.network.model.Response
import com.core.network.model.TokenResponse
import com.core.network.model.idResponse
import javax.inject.Inject

class NetworkDataSourceImpl @Inject constructor(
    private val networkApi: ApiService
) : NetworkDataSource {
    /**
     * alarm
     */
    override suspend fun postAlarm(alarmRequest: AddAlarmRequest): Response =
        networkApi.postAlarm(alarmRequest)

    override suspend fun getAddAlarmData(): AlarmAddResponse =
        networkApi.getAddAlarmData()

    /**
     * auth
     */
    override suspend fun kakaoLogin(): TokenResponse = networkApi.kakaoLogin()


    /**
     * medicine
     */
    override suspend fun enrollMedicine(medicine: EnrollMedicineRequest): Response =
        networkApi.enrollMedicine(medicine = medicine)

    override suspend fun getMedicines(): MedicineListResponse =
        networkApi.getMedicines()

    /**
     * child-medicine
     */
    override suspend fun getDoseRecords(todayDate: String): List<Character> =
        networkApi.getDoseRecords(todayDate= todayDate).characterList


    /**
     * fcm
     */
    override suspend fun sendNotification(notificationRequest: NotificationRequest): idResponse =
        networkApi.sendNotification(notificationRequest)


    override suspend fun pathFCMToken(fcmTokenRequest: FcmTokenRequest): idResponse =
        networkApi.pathFCMToken(fcmTokenRequest)

}