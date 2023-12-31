package com.kusitms.hdmedi.feature.home.domain

import com.core.common.model.DomainResponse
import com.core.common.model.ProfileAlarmList
import com.kusitms.hdmedi.feature.home.domain.model.AddAlarmInfo
import com.kusitms.hdmedi.feature.home.domain.model.CharacterInfo
import com.kusitms.hdmedi.feature.home.domain.model.Notification
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun postAlarm(alarmInfo: AddAlarmInfo): Flow<DomainResponse>

    suspend fun getAddAlarmData(): Flow<List<CharacterInfo>>

    suspend fun getAlarmRecodes(date: String): Flow<List<ProfileAlarmList>>

    suspend fun postAlarmNotification(notification: Notification)

}