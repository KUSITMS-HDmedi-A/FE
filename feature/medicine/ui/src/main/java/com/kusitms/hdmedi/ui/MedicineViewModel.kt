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
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.model.Alarm
import com.core.common.model.WeekDate
import com.kusitms.hdmedi.feature.medicine.data.repo.MedicineRepositoryImpl
import com.kusitms.hdmedi.feature.medicine.domain.repo.MedicineRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MedicineViewModel @Inject constructor(
    val tokenDataStore: TokenDataStore,
    val service: ApiService
    val repository: MedicineRepositoryImpl
): ViewModel() {

    var token = MutableLiveData<String>()
    var characterMedicineList = MutableLiveData<MedicineListResponse>()
    

//    init {
//        characterMedicineList.value = mutableListOf<MedicineListResponse>()
//    }


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

    fun getManageMedicine() {

        viewModelScope.launch {

            var tempCharacterMedicine = mutableListOf<Medicine>()
//            var tempEnrollMedicine = mutableListOf<EnrolledMedicine>()
//            var tempMedicineInfo = mutableListOf<MedicineList>()


            getToken()

            Log.d("##", "viewModel access : ${token.value}")

            service.getMedicineManagement("Bearer ${token.value}")
                ?.enqueue(object : Callback<MedicineListResponse> {
                    override fun onResponse(
                        call: Call<MedicineListResponse>,
                        response: retrofit2.Response<MedicineListResponse>
                    ) {
                        if (response.isSuccessful) {
                            // 정상적으로 통신이 성공된 경우
                            var result: MedicineListResponse? = response.body()
                            Log.d("##", "onResponse 성공 : $result")
                            var code = result?.code!!.toInt()
                            var message = result?.message.toString()
                            var characterList = result?.characterList
                            for (i in 0 until characterList!!.size) {
//                                Log.d("로그", "characterList : ${result?.characterList!!.get(i).characterName}")
                                var tempEnrollMedicine = mutableListOf<EnrolledMedicine>()
                                var name = characterList.get(i).characterName
                                var enrolledMedicineList =
                                    characterList.get(i).enrollMedicineList
                                Log.d("로그1", "${enrolledMedicineList.size}")
                                for (j in 0 until enrolledMedicineList.size) {
                                    var tempMedicineInfo = mutableListOf<MedicineList>()
//                                    Log.d("로그", "enrolled : $enrolledMedicineList")
                                    var endDate =
                                        enrolledMedicineList.get(j).endDate
                                    var medicineCount =
                                        enrolledMedicineList.get(j).medicineCount
                                    var purpose =
                                        enrolledMedicineList.get(j).purpose
                                    var startDate =
                                        enrolledMedicineList.get(j).startDate
                                    var medicineList =
                                        enrolledMedicineList.get(j).medicineList
                                    for (m in 0 until medicineList.size) {
                                        Log.d("로그1", "medicineListSize : ${medicineList.size}")
                                        var description =
                                           medicineList.get(m).derections
                                        var name =
                                            medicineList.get(m).name
                                        var effectList =
                                            medicineList.get(m).effectList
                                        var m1 = MedicineList(description, effectList, name)
                                        tempMedicineInfo.add(m1)
                                    }
                                    var e1 = EnrolledMedicine(
                                        endDate,
                                        medicineCount,
                                        tempMedicineInfo,
                                        purpose,
                                        startDate
                                    )
                                    Log.d("로그1", "${tempMedicineInfo}")
                                    tempEnrollMedicine.add(e1)
//                                    Log.d("로그", "tempEnroll : $tempEnrollMedicine")
//                                    Log.d("로그", "tempEnrollSize : ${tempEnrollMedicine.size}")
                                }

                                var c1 = Medicine(name, tempEnrollMedicine)
                                tempCharacterMedicine.add(c1)
                                Log.d("로그", "tempCharacterMedicine : ${tempCharacterMedicine}")
                                Log.d("로그", "tempCharacterMedicineSize : ${tempCharacterMedicine.size}")
                            }


                            characterMedicineList.value = MedicineListResponse(tempCharacterMedicine, code, message)
//                            Log.d("로그", "character : ${characterMedicineList.value}")

                        } else {
                            // 통신이 실패한 경우(응답코드 3xx, 4xx 등)
                            var result: MedicineListResponse? = response.body()
                            val errorBody = response.errorBody()?.string() // 에러 응답 데이터를 문자열로 얻음
                            Log.d("##", "Error Response: $errorBody")
                            Log.d("##", "onResponse 실패")
                            Log.d("##", "onResponse 실패: " + response.code())
                        }
                    }

                    override fun onFailure(call: Call<MedicineListResponse>, t: Throwable) {
                        // 통신 실패
                        Log.d("##", "onFailure 에러: " + t.message.toString());
                    }
                })
        }
    }


    private val _selectedDate = MutableLiveData<String>("")
    val selectedDate get() = _selectedDate

    fun updateSelectedDate(date: String) {
        _selectedDate.value = date
    }

    fun getHistoryList(){
        viewModelScope.launch {

        }
    }

    val alarmList = listOf<Alarm>(
        Alarm(
            isDone = true,
            time = "9:00",
            label = "빈속에 먹으면 안됨. 꼭 아침 먹이고!",
            medicineCnt = 1,
        ),
        Alarm(
            isDone = false,
            time = "12:30",
            label = "빈속에 먹으면 안됨. 꼭 아침 먹이고!",
            medicineCnt = 4,
        ),
        Alarm(
            isDone = false,
            time = "22:00",
            label = "빈속에 먹으면 안됨. 꼭 아침 먹이고!",
            medicineCnt = 2,
        )
    )
}