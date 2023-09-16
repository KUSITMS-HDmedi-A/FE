package com.kusitms.hdmedi.feature.signin.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.Resource

import com.kusitms.hdmedi.feature.signin.domain.repo.SigninRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.math.sign

@HiltViewModel
class SigninViewModel @Inject constructor(
    val signinRepository: SigninRepository
) : ViewModel() {

    private val _stateHolder = MutableStateFlow(Resource.success(false))
    val stateHolder get() = _stateHolder

    private val fcm = signinRepository.getFCMToken()

    fun requestLogin(token: String) = viewModelScope.launch {
        if (fcm == null) return@launch

        _stateHolder.value = Resource.loading(null)
        signinRepository.saveAccessToken(token).collect {
            Log.d(javaClass.name, "kakaoToken = $it")
            signinRepository.requestLogin().collect { tokens ->
                withContext(Dispatchers.IO) {
                    signinRepository.saveJwtTokens(tokens.accessToken, tokens.refreshToken)
                        .collect {
                            Log.d(javaClass.name, "jwtToken = ${tokens.accessToken==it} ")
                            if (tokens.accessToken == it)
                                _stateHolder.value = Resource.success(true)
                        }
                }
            }
        }

    }
}
