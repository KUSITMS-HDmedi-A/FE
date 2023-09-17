package com.kusitms.hdmedi.feature.selfcheck.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.kusitms.hdmedi.feature.selfcheck.ui.databinding.FragmentAdhdBinding


class AdhdFragment : Fragment() {

    lateinit var fragmentAdhdBinding: FragmentAdhdBinding

    lateinit var navController: NavController

    lateinit var viewModel: SelfCheckQuestionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentAdhdBinding = FragmentAdhdBinding.inflate(inflater)

        viewModel = ViewModelProvider(requireActivity())[SelfCheckQuestionViewModel::class.java]

        fragmentAdhdBinding.run {
            toolbarAdhd.run {
                title = "자가진단"
            }
        }
        return fragmentAdhdBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        fragmentAdhdBinding.run {
            buttonAdhdStart.setOnClickListener {

                viewModel.getToken()
                Log.d("##","viewModel : ${viewModel.getToken()}")
                viewModel.getQuestion(viewModel.getToken().toString())
                navController.navigate(R.id.action_adhdFragment_to_selfCheckPersonFragment)
            }
        }
    }
}