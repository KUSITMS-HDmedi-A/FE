package com.kusitms.hdmedi.feature.signin.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.Resource

import com.kusitms.hdmedi.feature.signin.domain.repo.SigninRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SigninViewModel @Inject constructor(
    val signinRepository: SigninRepository
): ViewModel() {

    private val _stateHolder= MutableStateFlow(Resource.success(false))
    val stateHolder get() = _stateHolder

    fun requestLogin(token: String) = viewModelScope.launch {
        _stateHolder.value = Resource.loading(null)
        signinRepository.saveSocialToken(token).collect{
            Log.d("signin", "token = $it")
            signinRepository.requestLogin().collect {tokens ->
                signinRepository.saveJwtTokens(tokens.accessToken, tokens.refreshToken).collect{
                    if (tokens.accessToken == it) _stateHolder.value = Resource.success(true)
                }
            }
        }

    }
}
