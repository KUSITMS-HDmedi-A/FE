package com.kusitms.hdmedi.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.kusitms.hdmedi.ui.databinding.FragmentRegisterMedicineDateBinding

class RegisterMedicineDateFragment : Fragment() {

    lateinit var fragmentRegisterMedicineDateBinding: FragmentRegisterMedicineDateBinding

    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentRegisterMedicineDateBinding = FragmentRegisterMedicineDateBinding.inflate(inflater)

        fragmentRegisterMedicineDateBinding.run {
            toolbarRegisterMedicineDate.run {
                title = "약 등록"
            }
        }
        return fragmentRegisterMedicineDateBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        fragmentRegisterMedicineDateBinding.run {
            buttonRegisterMedicineDateNext.setOnClickListener {
                navController.navigate(R.id.action_registerMedicineDateFragment_to_registerMedicineInfoFragment)
            }
        }
    }
}