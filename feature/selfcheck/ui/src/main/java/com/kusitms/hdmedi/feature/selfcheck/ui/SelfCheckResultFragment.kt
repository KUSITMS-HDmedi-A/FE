package com.kusitms.hdmedi.feature.selfcheck.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.kusitms.hdmedi.feature.selfcheck.ui.databinding.FragmentSelfCheckResultBinding

class SelfCheckResultFragment : Fragment() {

    lateinit var fragmentSelfCheckResultBinding: FragmentSelfCheckResultBinding

    lateinit var navController: NavController

    lateinit var viewModel: SelfCheckQuestionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentSelfCheckResultBinding = FragmentSelfCheckResultBinding.inflate(inflater)

        viewModel = ViewModelProvider(requireActivity())[SelfCheckQuestionViewModel::class.java]
        viewModel.run {
            scoreData.observe(requireActivity()) {
                fragmentSelfCheckResultBinding.textViewSelfCheckResultScore.text = it.toString()
            }
            resultData.observe(requireActivity()) {
                fragmentSelfCheckResultBinding.textViewSelfCheckResultContent.text = "ADHD 진단 결과, ${it.toString()}"
            }
        }

        fragmentSelfCheckResultBinding.run {
            toolbarSelfCheckResult.run {
                title = "자가진단"

                inflateMenu(R.menu.close_menu)

                setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.item_close -> {
                            navController.navigate(R.id.action_selfCheckResultFragment_to_selfCheckFragment)
                        }
                        else -> { }
                    }
                    true
                }

            }
        }
        return fragmentSelfCheckResultBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
    }
}