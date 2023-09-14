package com.kusitms.hdmedi.feature.medicine.data.di

import com.kusitms.hdmedi.feature.medicine.data.repo.MedicineRepositoryImpl
import com.kusitms.hdmedi.feature.medicine.domain.repo.MedicineRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DomainLayerModule {
    @Binds
    fun MedicineRepositoryImpl.binds(): MedicineRepository
}