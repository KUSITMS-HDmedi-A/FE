package com.kusitms.hdmedi.feature.home.domain

import com.core.common.model.DomainResponse
import com.kusitms.hdmedi.feature.home.domain.model.AddAlarmInfo
import com.kusitms.hdmedi.feature.home.domain.model.CharacterInfo
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun postAlarm(alarmInfo: AddAlarmInfo): Flow<DomainResponse>

    suspend fun getAddAlarmData(): Flow<List<CharacterInfo>>

}