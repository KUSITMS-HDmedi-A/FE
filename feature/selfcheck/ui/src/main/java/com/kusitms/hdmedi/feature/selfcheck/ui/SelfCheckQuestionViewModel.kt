package com.kusitms.hdmedi.feature.selfcheck.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.network.ApiService
import com.core.network.model.ADHDQuestionResponse
import com.core.network.model.ADHDResultRequest
import com.core.network.model.ADHDResultResponse
import com.core.network.model.Question
import com.core.network.utils.TokenDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SelfCheckQuestionViewModel @Inject constructor(
    val tokenDataStore: TokenDataStore,
    val service: ApiService
) : ViewModel() {

    // 질문 요청
    var characterNameList = MutableLiveData<MutableList<String>>()
    var questionList = MutableLiveData<MutableList<Question>>()
    var questionBlueList = MutableLiveData<MutableList<String>>()
    var questionDescriptionList = MutableLiveData<MutableList<String>>()
    var token = MutableLiveData<String>()

    // 결과 반환
    var resultData = MutableLiveData<String>()
    var scoreData = MutableLiveData<Int>()


    init {
        characterNameList.value = mutableListOf<String>()
        questionList.value = mutableListOf<Question>()
        questionBlueList.value = mutableListOf<String>()
        questionDescriptionList.value = mutableListOf<String>()
    }

//    suspend fun getToken() {
//        token.postValue(tokenDataStore.getToken().toString())
//    }

    fun getToken() {
        viewModelScope.launch {
            val response = tokenDataStore.getToken()
            Log.d("##", "response : $response")
            token.postValue(response.toString())
        }
    }



    fun getQuestion(accessToken: String) {

        var tempNameList = mutableListOf<String>()
        var tempBlueList = mutableListOf<String>()
        var tempDescriptionList = mutableListOf<String>()

        getToken()
        Log.d("##", "viewModel access : ${token.value}")

        Log.d("##", "access : $accessToken")

        service.getAdhdQuestion("Bearer ${accessToken}")
            ?.enqueue(object :
                Callback<ADHDQuestionResponse> {
                override fun onResponse(
                    call: Call<ADHDQuestionResponse>,
                    response: Response<ADHDQuestionResponse>
                ) {
                    if (response.isSuccessful) {
                        // 정상적으로 통신이 성공된 경우
                        var result: ADHDQuestionResponse? = response.body()
                        Log.d("##", "onResponse 성공 : $result")

                        if (result!!.code == 400) {

                        } else {
                            if(result?.character != null) {
                                for (c in 0 until result?.character!!.size) {
                                    val character = result?.character!!.get(c).toString()
                                    tempNameList.add(character)
                                }
                            }
                            for (i in 0 until result?.questionsList!!.size) {
                                Log.d("##", "${result?.questionsList}")
                                val blue = result?.questionsList!!.get(i).blue
                                val description = result?.questionsList!!.get(i).description

                                tempBlueList.add(blue)
                                tempDescriptionList.add(description)
                            }

                            characterNameList.value = tempNameList
                            Log.d("##", "character : ${characterNameList.value}")
                            questionBlueList.value = tempBlueList
                            questionDescriptionList.value = tempDescriptionList
                        }
                    } else {
                        // 통신이 실패한 경우(응답코드 3xx, 4xx 등)
                        var result: ADHDQuestionResponse? = response.body()
                        val errorBody = response.errorBody()?.string() // 에러 응답 데이터를 문자열로 얻음
                        Log.d("##", "Error Response: $errorBody")
                        Log.d("##", "onResponse 실패")
                        Log.d("##", "onResponse 실패: " + response.code())
                    }
                }

                override fun onFailure(call: Call<ADHDQuestionResponse>, t: Throwable) {
                    // 통신 실패
                    Log.d("##", "onFailure 에러: " + t.message.toString());
                }
            })
    }


    fun getResult(accessToken: String, result: ADHDResultRequest) {

        getToken()
        Log.d("##", "viewModel access : ${token.value}")

        Log.d("##", "access : $accessToken")

        service.sendAdhdResult("Bearer ${accessToken}", result)
            ?.enqueue(object :
                Callback<ADHDResultResponse> {
                override fun onResponse(
                    call: Call<ADHDResultResponse>,
                    response: Response<ADHDResultResponse>
                ) {
                    if (response.isSuccessful) {
                        // 정상적으로 통신이 성공된 경우
                        var result: ADHDResultResponse? = response.body()
                        Log.d("##", "onResponse 성공 : $result")

                        resultData.postValue(result?.result.toString())
                        scoreData.postValue(result?.score)
                        Log.d("##", "${resultData.value}")
                        Log.d("##", "${scoreData.value}")

                    } else {
                        // 통신이 실패한 경우(응답코드 3xx, 4xx 등)
                        var result: ADHDResultResponse? = response.body()
                        val errorBody = response.errorBody()?.string() // 에러 응답 데이터를 문자열로 얻음
                        Log.d("##", "Error Response: $errorBody")
                        Log.d("##", "onResponse 실패")
                        Log.d("##", "onResponse 실패: " + response.code())
                    }
                }

                override fun onFailure(call: Call<ADHDResultResponse>, t: Throwable) {
                    // 통신 실패
                    Log.d("##", "onFailure 에러: " + t.message.toString());
                }
            })
    }
}