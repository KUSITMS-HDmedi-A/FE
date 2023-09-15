package com.kusitms.hdmedi.feature.signin.domain.usecases

import com.core.common.UiEvent
import com.kusitms.hdmedi.feature.signin.domain.repo.SigninRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import javax.inject.Inject
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class SigninUseCase @Inject constructor(
    private val signinRepository: SigninRepository
){
    operator fun invoke(token: String) = flow<UiEvent<Boolean>> {
        emit(UiEvent.Loading())
        signinRepository.saveSocialToken(token = token).collect{
            if (it != null && it == token){
                signinRepository.requestLogin().also {
                    signinRepository.saveJwtTokens(it.accessToken, it.refreshToken).collect{
                        emit(UiEvent.Success(true))
                    }
                }
            }
        }
    }.catch {
        emit(UiEvent.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)
}