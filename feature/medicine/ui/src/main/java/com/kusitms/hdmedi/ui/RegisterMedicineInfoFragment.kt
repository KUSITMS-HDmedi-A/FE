package com.kusitms.hdmedi.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getColorStateList
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.datepicker.MaterialDatePicker.Builder.datePicker
import com.kusitms.hdmedi.ui.databinding.FragmentRegisterMedicineInfoBinding


class RegisterMedicineInfoFragment : Fragment() {

    lateinit var fragmentRegisterMedicineInfoBinding: FragmentRegisterMedicineInfoBinding

    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentRegisterMedicineInfoBinding = FragmentRegisterMedicineInfoBinding.inflate(inflater)
        fragmentRegisterMedicineInfoBinding.run {

            linearLayoutRegisterMedicineInfoAdd.visibility = View.GONE
            buttonRegisterMedicineInfoAdd.visibility = View.GONE

            toolbarRegisterMedicineInfo.run {
                title = "약 등록"
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
                linearLayoutRegisterMedicineInfoAdd.removeView(itemView)
                linearLayoutRegisterMedicineInfoAdd.addView(itemView)
            }
        }
        return fragmentRegisterMedicineInfoBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        fragmentRegisterMedicineInfoBinding.run {
            buttonRegisterMedicineInfoNext.setOnClickListener {
                navController.navigate(R.id.action_registerMedicineInfoFragment_to_registerMedicineCompleteFragment)
            }
        }
    }
}