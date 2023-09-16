package com.kusitms.hdmedi.feature.home.data

import com.core.network.datasource.NetworkDataSource
import com.kusitms.hdmedi.feature.home.domain.model.CharacterInfo
import com.kusitms.hdmedi.feature.home.domain.HomeRepository
import com.kusitms.hdmedi.feature.home.domain.model.AddAlarmInfo
import javax.inject.Inject
import com.core.common.model.DomainResponse
import com.core.network.model.AddAlarmRequest
import com.core.network.model.AlarmAddResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class HomeRepositoryImpl @Inject constructor(
    private val dataSource: NetworkDataSource,
): HomeRepository {
    override suspend fun postAlarm(alarmInfo: AddAlarmInfo): Flow<DomainResponse> = flow<DomainResponse> {
        val request = AddAlarmRequest(
            character = alarmInfo.character,
            day = alarmInfo.day,
            endDate = alarmInfo.endDate,
            label = alarmInfo.label ?: "",
            medicine = alarmInfo.medicine,
            startDate = alarmInfo.startDate,
            time = alarmInfo.time
        )
        dataSource.postAlarm(request).code
    }.flowOn(Dispatchers.IO)


    override suspend fun getAddAlarmData(): Flow<List<CharacterInfo>> = flow<List<CharacterInfo>> {
        dataSource.getAddAlarmData().characterData.map {
            CharacterInfo(
                characterName = it.characterName,
                medicines =  it.medicine.map { it.purpose }
            )
        }
    }.flowOn(Dispatchers.IO)

}