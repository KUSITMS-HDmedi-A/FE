package com.kusitms.hdmedi.feature.medicine.domain.use_cases

import com.core.common.UiEvent
import com.kusitms.hdmedi.feature.medicine.domain.model.MedicinePeriod
import com.kusitms.hdmedi.feature.medicine.domain.repo.MedicineRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetMedicinesUseCase @Inject constructor(
    private val medicineRepository: MedicineRepository
) {
    operator fun invoke() = flow<UiEvent<List<MedicinePeriod>>> {
        emit(UiEvent.Loading())
    }.catch {
        emit(UiEvent.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)
}