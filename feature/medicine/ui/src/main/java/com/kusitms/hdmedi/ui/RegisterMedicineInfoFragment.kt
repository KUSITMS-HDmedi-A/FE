package com.kusitms.hdmedi.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.content.res.AppCompatResources.getColorStateList
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.core.network.model.EnrollMedicineRequest
import com.google.android.material.datepicker.MaterialDatePicker.Builder.datePicker
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.kusitms.hdmedi.ui.databinding.AddMedicineBinding
import com.kusitms.hdmedi.ui.databinding.FragmentRegisterMedicineInfoBinding


class RegisterMedicineInfoFragment : Fragment() {

    lateinit var fragmentRegisterMedicineInfoBinding: FragmentRegisterMedicineInfoBinding

    lateinit var navController: NavController

    val retrievedTextList = mutableListOf<String>()

    lateinit var viewModel: MedicineViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentRegisterMedicineInfoBinding = FragmentRegisterMedicineInfoBinding.inflate(inflater)
        viewModel = ViewModelProvider(requireActivity())[MedicineViewModel::class.java]
        viewModel.run {
            token.observe(requireActivity()) {

            }
        }
        viewModel.getToken()

        fragmentRegisterMedicineInfoBinding.run {

            linearLayoutRegisterMedicineInfoAdd.visibility = View.GONE
            buttonRegisterMedicineInfoAdd.visibility = View.GONE

            toolbarRegisterMedicineInfo.run {
                title = "약 등록"

                // back 버튼 설정
                setNavigationIcon(R.drawable.arrow_back)

                setNavigationOnClickListener {
                    navController.popBackStack()
                }
            }

            buttonRegisterMedicineInfoPicture.setOnClickListener {
                // 카메라 앱 실행
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(intent, 0)
            }

            buttonRegisterMedicineInfoWrite.setOnClickListener {
                linearLayoutRegisterMedicineInfoAdd.visibility = View.VISIBLE
                buttonRegisterMedicineInfoAdd.visibility = View.VISIBLE
                buttonRegisterMedicineInfoWrite.setStrokeColorResource(R.color.main_blue)
                buttonRegisterMedicineInfoWrite.setTextColor(resources.getColor(R.color.main_blue))
            }

            buttonRegisterMedicineInfoAdd.setOnClickListener {
                val itemView = inflater.inflate(R.layout.add_medicine, null)
                var medicineInfo = itemView.findViewById<TextInputEditText>(R.id.textInputEditTextAddMedicine)
                linearLayoutRegisterMedicineInfoAdd.removeView(itemView)
                linearLayoutRegisterMedicineInfoAdd.addView(itemView,0)
            }
        }
        return fragmentRegisterMedicineInfoBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        fragmentRegisterMedicineInfoBinding.run {
            buttonRegisterMedicineInfoNext.setOnClickListener {
                if(textInputEditTextRegisterMedicineInfoMedicine.text!!.isNotEmpty()) {
                    retrievedTextList.add(textInputEditTextRegisterMedicineInfoMedicine.text.toString())
                }
                // 컨테이너 내의 모든 TextInputLayout에서 입력된 텍스트를 가져와서 리스트에 추가
                for (i in linearLayoutRegisterMedicineInfoAdd.childCount-2 downTo 0) {
                    val view: View = linearLayoutRegisterMedicineInfoAdd.getChildAt(i)
                    if (view is LinearLayout) {
                        val editText: EditText = view.findViewById(R.id.textInputEditTextAddMedicine)
                        val enteredText = editText.text.toString()
                        if(enteredText.isNotEmpty()) {
                            retrievedTextList.add(enteredText)
                        }
                    }
                }

                Log.d("##", "retrieved : ${retrievedTextList}")
                var medicineList = requireArguments().getStringArrayList("medicine")
                Log.d("##", "$medicineList")
                var purpose = medicineList?.get(0).toString()
                var startDate = medicineList?.get(1).toString()
                var endDate = medicineList?.get(2).toString()

                var e1 = EnrollMedicineRequest(purpose, startDate, endDate,"김리아", retrievedTextList)
                Log.d("##", "$e1")

                viewModel.enrollMedicine(e1)
                navController.navigate(R.id.action_registerMedicineInfoFragment_to_registerMedicineCompleteFragment)
            }
        }
    }
}