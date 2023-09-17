package com.kusitms.hdmedi.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.network.ApiService
import com.core.network.model.ADHDQuestionResponse
import com.core.network.model.EnrollMedicineRequest
import com.core.network.model.EnrolledMedicine
import com.core.network.model.Medicine
import com.core.network.model.MedicineList
import com.core.network.model.MedicineListResponse
import com.core.network.model.Response
import com.core.network.utils.TokenDataStore
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import javax.inject.Inject

@HiltViewModel
class MedicineViewModel @Inject constructor(
    val tokenDataStore: TokenDataStore,
    val service: ApiService
) : ViewModel() {

    var token = MutableLiveData<String>()
    var characterMedicineList = MutableLiveData<MutableList<Medicine>>()

    init {
        characterMedicineList.value = mutableListOf<Medicine>()
    }


    fun getToken() {
        viewModelScope.launch {
            val response = tokenDataStore.getToken()
            Log.d("##", "response : $response")
            token.postValue(response.toString())
        }
    }


    fun enrollMedicine(medicine: EnrollMedicineRequest) {

        getToken()

        Log.d("##", "viewModel access : ${token.value}")

        service.enrollMedicine("Bearer ${token.value}", medicine)?.enqueue(object : Callback<Response> {
            override fun onResponse(
                call: Call<Response>,
                response: retrofit2.Response<Response>
            ) {
                    if (response.isSuccessful) {
                        // 정상적으로 통신이 성공된 경우
                        var result: Response? = response.body()
                        Log.d("##", "onResponse 성공 : $result")

                    } else {
                        // 통신이 실패한 경우(응답코드 3xx, 4xx 등)
                        var result: Response? = response.body()
                        val errorBody = response.errorBody()?.string() // 에러 응답 데이터를 문자열로 얻음
                        Log.d("##", "Error Response: $errorBody")
                        Log.d("##", "onResponse 실패")
                        Log.d("##", "onResponse 실패: " + response.code())
                    }
                }

                override fun onFailure(call: Call<Response>, t: Throwable) {
                    // 통신 실패
                    Log.d("##", "onFailure 에러: " + t.message.toString());
                }
            })
    }

}