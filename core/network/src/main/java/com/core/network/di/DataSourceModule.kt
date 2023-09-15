package com.core.network.di

import com.core.network.datasource.NetworkDataSource
import com.core.network.datasource.NetworkDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
// repository에서 구현체 대신 인터페이스 파라미터로 주기 위해
interface DataSourceModule {
    @Binds
    fun NetworkDataSourceImpl.binds(): NetworkDataSource
}