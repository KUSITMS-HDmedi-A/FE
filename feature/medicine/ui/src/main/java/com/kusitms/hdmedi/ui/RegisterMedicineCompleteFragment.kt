package com.kusitms.hdmedi.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.kusitms.hdmedi.ui.databinding.FragmentRegisterMedicineCompleteBinding

class RegisterMedicineCompleteFragment : Fragment() {

    lateinit var fragmentRegisterMedicineCompleteBinding: FragmentRegisterMedicineCompleteBinding

    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentRegisterMedicineCompleteBinding = FragmentRegisterMedicineCompleteBinding.inflate(inflater)

        return fragmentRegisterMedicineCompleteBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        fragmentRegisterMedicineCompleteBinding.run {
            buttonRegisterMedicineCompleteAdd.setOnClickListener {
                navController.navigate(R.id.action_registerMedicineCompleteFragment_to_registerMedicinePurposeFragment)
            }
            buttonRegisterMedicineComplete.setOnClickListener {
                navController.navigate(R.id.action_registerMedicineCompleteFragment_to_medicineFragment)
            }
        }
    }
}