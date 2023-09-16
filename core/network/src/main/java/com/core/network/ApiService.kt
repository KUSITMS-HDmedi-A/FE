package com.core.network

import com.core.network.model.AddAlarmRequest
import com.core.network.model.EnrollMedicineRequest
import com.core.network.model.FcmTokenRequest
import com.core.network.model.MedicineListResponse
import com.core.network.model.NotificationRequest
import com.core.network.model.Response
import com.core.network.model.TokenResponse
import com.core.network.model.idResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST

interface ApiService {
    /**
     * adhd 진단
     */

    /**
     * alamm 알람
     */
    @POST("/api/alarm/add")
    suspend fun postAlarm(@Body alarmRequest: AddAlarmRequest): Response

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

    /**
     * fcm
     */
    @POST("/api/notification")
    suspend fun sendNotification(@Body notificationRequest: NotificationRequest): idResponse

    @PATCH("/api/notification/token")
    suspend fun pathFCMToken(@Body fcmTokenRequest: FcmTokenRequest): idResponse
}