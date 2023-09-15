package com.kusitms.hdmedi.feature.selfcheck.ui

import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.kusitms.hdmedi.feature.selfcheck.ui.databinding.FragmentSelfCheckQuestionCompleteBinding

class SelfCheckQuestionCompleteFragment : Fragment() {

    lateinit var fragmentSelfCheckQuestionCompleteBinding: FragmentSelfCheckQuestionCompleteBinding

    lateinit var navController: NavController

    val handler = Handler()
    val delayMillis = 3000 // 3초

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentSelfCheckQuestionCompleteBinding = FragmentSelfCheckQuestionCompleteBinding.inflate(inflater)

        fragmentSelfCheckQuestionCompleteBinding.run {
            toolbarSelfCheckComplete.run {
                title = "자가진단"
            }

            handler.postDelayed({
                // 화면 전환 코드
                navController.navigate(R.id.action_selfCheckQuestionCompleteFragment_to_selfCheckResultFragment)
            }, delayMillis.toLong())
        }
        return fragmentSelfCheckQuestionCompleteBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
    }
}