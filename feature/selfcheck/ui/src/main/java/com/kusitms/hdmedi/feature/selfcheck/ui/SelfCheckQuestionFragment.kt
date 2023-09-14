package com.kusitms.hdmedi.feature.selfcheck.ui

import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.kusitms.hdmedi.feature.selfcheck.ui.databinding.FragmentSelfCheckQuestionBinding

class SelfCheckQuestionFragment : Fragment() {

    lateinit var fragmentSelfCheckQuestionBinding: FragmentSelfCheckQuestionBinding

    var imageList = listOf<Int>(
        R.drawable.selfcheck1,
        R.drawable.selfcheck2,
        R.drawable.selfcheck3,
        R.drawable.selfcheck4,
        R.drawable.selfcheck5,
        R.drawable.selfcheck6,
        R.drawable.selfcheck7,
        R.drawable.selfcheck8,
        R.drawable.selfcheck9,
        R.drawable.selfcheck10,
        R.drawable.selfcheck11,
        R.drawable.selfcheck12,
        R.drawable.selfcheck13,
        R.drawable.selfcheck14,
        R.drawable.selfcheck15,
        R.drawable.selfcheck16,
        R.drawable.selfcheck17,
        R.drawable.selfcheck18
    )

    var scoreList = mutableListOf<Int>()

    var i = 0

    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentSelfCheckQuestionBinding = FragmentSelfCheckQuestionBinding.inflate(inflater)

        fragmentSelfCheckQuestionBinding.run {
            toolbarSelfCheckPerson.run {
                title = "자가진단"
            }

            buttonSelfCheckQuestionAnswer1.setOnClickListener {
                scoreList.add(0)
                Log.d("HDMEDI LOG","score: $scoreList")
                changeQuestion()
            }
            buttonSelfCheckQuestionAnswer2.setOnClickListener {
                scoreList.add(1)
                Log.d("HDMEDI LOG","score: $scoreList")
                changeQuestion()
            }
            buttonSelfCheckQuestionAnswer3.setOnClickListener {
                scoreList.add(2)
                Log.d("HDMEDI LOG","score: $scoreList")
                changeQuestion()
            }
            buttonSelfCheckQuestionAnswer4.setOnClickListener {
                scoreList.add(3)
                Log.d("HDMEDI LOG","score: $scoreList")
                changeQuestion()
            }
        }
        return fragmentSelfCheckQuestionBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
    }

    fun changeQuestion() {
        fragmentSelfCheckQuestionBinding.run {
            if(i < imageList.size -1) {
                i++
                imageViewSelfCheckQuestion.setImageResource(imageList[i])
                progressSelfCheckPerson.progress = i + 1
            }
            else {
                navController.navigate(R.id.action_selfCheckQuestionFragment_to_selfCheckQuestionCompleteFragment)
            }
        }
    }
}