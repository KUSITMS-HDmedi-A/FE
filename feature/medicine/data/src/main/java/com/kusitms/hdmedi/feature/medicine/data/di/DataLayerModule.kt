package com.kusitms.hdmedi.feature.medicine.data.di

import com.core.network.datasource.NetworkDataSource
import com.kusitms.hdmedi.feature.medicine.domain.repo.MedicineRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
internal abstract class DataLayerModule {

}