package com.kusitms.hdmedi.feature.medicine.data.repo

import com.core.network.datasource.NetworkDataSource
import com.kusitms.hdmedi.feature.medicine.data.mapper.toDomainMedicineList
import com.kusitms.hdmedi.feature.medicine.domain.model.MedicinePeriod
import com.kusitms.hdmedi.feature.medicine.domain.repo.MedicineRepository
import javax.inject.Inject

class MedicineRepositoryImpl @Inject constructor(
    private val dataSource: NetworkDataSource
): MedicineRepository {
    override suspend fun getMedicines(): List<MedicinePeriod> =
        dataSource.getMedicines().toDomainMedicineList()
}