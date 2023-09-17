package com.kusitms.hdmedi.feature.medicine.data.di

import androidx.lifecycle.ViewModel
import com.core.network.datasource.NetworkDataSource
import com.kusitms.hdmedi.feature.medicine.data.repo.MedicineRepositoryImpl
import com.kusitms.hdmedi.feature.medicine.domain.repo.MedicineRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
internal abstract class DataLayerModule {

    @Binds
    abstract fun bindsMedicineRepository(
        repository: MedicineRepositoryImpl
    ): MedicineRepository

    @InstallIn(SingletonComponent::class)
    @Module
    internal object FakeModule {
        @Singleton
        fun provideMedicineRepo(dataSource: NetworkDataSource): MedicineRepository =
            MedicineRepositoryImpl(dataSource = dataSource)
    }
}