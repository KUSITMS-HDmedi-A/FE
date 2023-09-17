package com.core.network

import com.core.network.model.AddAlarmRequest
import com.core.network.model.AlarmAddResponse
import com.core.network.model.DoseRecordResponse
import com.core.network.model.ADHDQuestionResponse
import com.core.network.model.ADHDResultRequest
import com.core.network.model.ADHDResultResponse
import com.core.network.model.EnrollMedicineRequest
import com.core.network.model.FcmTokenRequest
import com.core.network.model.MedicineListResponse
import com.core.network.model.NotificationRequest
import com.core.network.model.Response
import com.core.network.model.TokenResponse
import com.core.network.model.idResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    /**
     * adhd 진단
     */
    @GET("/api/adhd/test")
    fun getAdhdQuestion(@Header("Authorization") token: String) : Call<ADHDQuestionResponse>

    @POST("/api/adhd/test/result")
    fun sendAdhdResult(@Header("Authorization") token: String, @Body result: ADHDResultRequest): Call<ADHDResultResponse>

    /**
     * alamm 알람
     */
    @POST("/api/alarm/add")
    suspend fun postAlarm(@Body alarmRequest: AddAlarmRequest): Response

    @GET("/api/alarm/add-page")
    suspend fun getAddAlarmData(): AlarmAddResponse

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

    /**
     * child medicine
     */
    @GET("/api/child-medicine/dose-record/{todayDate}")
    suspend fun getDoseRecords(@Path("todayDate") todayDate: String): DoseRecordResponse

    /**
     * fcm
     */
    @POST("/api/notification")
    suspend fun sendNotification(@Body notificationRequest: NotificationRequest): idResponse

    @PATCH("/api/notification/token")
    suspend fun pathFCMToken(@Body fcmTokenRequest: FcmTokenRequest): idResponse

    /**
     * user (home)
     */
}