package com.kusitms.hdmedi.feature.home.data

import com.core.common.model.Alarm
import com.core.network.datasource.NetworkDataSource
import com.kusitms.hdmedi.feature.home.domain.model.CharacterInfo
import com.kusitms.hdmedi.feature.home.domain.HomeRepository
import com.kusitms.hdmedi.feature.home.domain.model.AddAlarmInfo
import javax.inject.Inject
import com.core.common.model.DomainResponse
import com.core.common.model.ProfileAlarmList
import com.core.network.model.AddAlarmRequest
import com.core.network.model.AlarmAddResponse
import com.core.network.model.NotificationRequest
import com.kusitms.hdmedi.feature.home.domain.model.Notification
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn

class HomeRepositoryImpl @Inject constructor(
    private val dataSource: NetworkDataSource,
) : HomeRepository {
    override suspend fun postAlarm(alarmInfo: AddAlarmInfo): Flow<DomainResponse> =
        flow<DomainResponse> {
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
                medicines = it.medicine.map { it.purpose }
            )
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getAlarmRecodes(date: String): Flow<List<ProfileAlarmList>> {
        val list = mutableListOf<ProfileAlarmList>()
        dataSource.getDoseRecords(todayDate = date).forEach {
            val alarmList = it.doseAlarmList.map {
                Alarm(
                    isDone = it.doseSign,
                    time = it.time,
                    label = it.label,
                    medicineCnt = it.count
                )
            }
            list.add(
                ProfileAlarmList(name = it.name, alarmList = alarmList)
            )
        }
        return flowOf(list).flowOn(Dispatchers.IO)
    }

    override suspend fun postAlarmNotification(notification: Notification) {
        val notificationRequest = NotificationRequest(
            body = notification.body ?: "",
            title = notification.title,
            targetUserId = 0
        )
        flowOf(dataSource.sendNotification(notificationRequest)).flowOn(Dispatchers.IO)
    }

}