package com.kusitms.hdmedi.feature.medicine.data.di

import com.core.network.datasource.NetworkDataSource
import com.kusitms.hdmedi.feature.medicine.data.repo.MedicineRepositoryImpl
import com.kusitms.hdmedi.feature.medicine.domain.repo.MedicineRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataLayerModule {
    @Provides
    fun provideMedicineRepo(dataSource: NetworkDataSource): MedicineRepository =
        MedicineRepositoryImpl(dataSource = dataSource)
}