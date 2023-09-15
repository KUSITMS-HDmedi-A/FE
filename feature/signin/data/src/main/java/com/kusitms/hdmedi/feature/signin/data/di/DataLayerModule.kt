package com.kusitms.hdmedi.feature.signin.data.di

import com.core.common.SharedPreferenceManager
import com.core.network.datasource.NetworkDataSource
import com.core.network.utils.TokenDataStore
import com.kusitms.hdmedi.feature.signin.data.repo.SigninRepositoryImpl
import com.kusitms.hdmedi.feature.signin.domain.repo.SigninRepository
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
    abstract fun bindsSigninRepository(
        repository: SigninRepositoryImpl
    ): SigninRepository

    @InstallIn(SingletonComponent::class)
    @Module
    internal object FakeModule {
        @Singleton
        fun provideSigninRepo(
            dataSource: NetworkDataSource,
            tokenDataStore: TokenDataStore,
            sharedPreferenceManager: SharedPreferenceManager
        ): SigninRepository =
            SigninRepositoryImpl(dataSource = dataSource, tokenSource = tokenDataStore,sharedPreferences = sharedPreferenceManager)
    }
}