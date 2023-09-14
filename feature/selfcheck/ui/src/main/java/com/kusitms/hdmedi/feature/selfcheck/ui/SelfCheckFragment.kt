package com.kusitms.hdmedi.feature.selfcheck.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.kusitms.hdmedi.feature.selfcheck.ui.databinding.FragmentSelfCheckBinding

class SelfCheckFragment : Fragment() {

    lateinit var fragmentSelfCheckBinding: FragmentSelfCheckBinding

    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentSelfCheckBinding = FragmentSelfCheckBinding.inflate(inflater)

        fragmentSelfCheckBinding.run {
            toolbarSelfCheck.run {
                title = "자가진단"
            }
        }
        return fragmentSelfCheckBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        fragmentSelfCheckBinding.run {
            buttonSelfCheckStart.setOnClickListener {
                navController.navigate(R.id.action_selfCheckFragment_to_adhdFragment)
            }
        }
    }
}