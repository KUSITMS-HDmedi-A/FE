package com.kusitms.hdmedi.feature.medicine.data.mapper

import com.core.network.model.MedicineListResponse
import com.kusitms.hdmedi.feature.medicine.domain.model.Medicine
import com.kusitms.hdmedi.feature.medicine.domain.model.MedicinePeriod

fun MedicineListResponse.toDomainMedicineList(): List<MedicinePeriod> = this.list.map {
    MedicinePeriod(
        it.startDate,
        it.endDate,
        it.prescription,
        it.purpose,
        it.medicines.map {
            Medicine(it.img, it.name, it.precautions)
        }
    )

}