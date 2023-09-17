package com.kusitms.hdmedi.feature.home.data

import com.core.network.datasource.NetworkDataSource
import com.kusitms.hdmedi.feature.home.domain.HomeRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataLayerModule {
    @Binds
    abstract fun bindsHomeRepository(
        repository: HomeRepositoryImpl
    ): HomeRepository

    @InstallIn(SingletonComponent::class)
    @Module
    internal object FakeModule {
        @Singleton
        fun provideHomeRepo(
            dataSource: NetworkDataSource
        ): HomeRepository = HomeRepositoryImpl(dataSource= dataSource)
    }
}