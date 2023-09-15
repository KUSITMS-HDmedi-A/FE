package com.kusitms.hdmedi.feature.signin.ui.di

import com.kusitms.hdmedi.feature.signin.domain.repo.SigninRepository
import com.kusitms.hdmedi.feature.signin.domain.usecases.SigninUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class UiLayerModule {
    @Provides
    fun provideSigninUseCase(repo: SigninRepository) =
        SigninUseCase(signinRepository = repo)
}