package com.kusitms.hdmedi.ui

import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.kusitms.hdmedi.ui.databinding.FragmentRegisterMedicinePurposeBinding

class RegisterMedicinePurposeFragment : Fragment() {

    lateinit var fragmentRegisterMedicinePurposeBinding: FragmentRegisterMedicinePurposeBinding

    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentRegisterMedicinePurposeBinding = FragmentRegisterMedicinePurposeBinding.inflate(inflater)

        fragmentRegisterMedicinePurposeBinding.run {
            toolbarRegisterMedicinePurpose.run {
                title = "약 등록"

                // back 버튼 설정
                setNavigationIcon(R.drawable.arrow_back)

                setNavigationOnClickListener {
                    navController.popBackStack()
                }
            }
        }
        return fragmentRegisterMedicinePurposeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        fragmentRegisterMedicinePurposeBinding.run {
            buttonRegisterMedicinePurposeNext.setOnClickListener {
                navController.navigate(R.id.action_registerMedicinePurposeFragment_to_registerMedicineDateFragment)
            }
        }
    }
}