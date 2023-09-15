package com.kusitms.hdmedi.feature.medicine.domain.repo

import com.kusitms.hdmedi.feature.medicine.domain.model.MedicinePeriod

// todo 임의로 예시를 위해 짠 코드이니 로직만 봐주세요!
interface MedicineRepository {
    suspend fun getMedicines(): List<MedicinePeriod>
}