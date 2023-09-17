package com.kusitms.hdmedi.feature.selfcheck.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kusitms.hdmedi.feature.selfcheck.ui.databinding.FragmentSelfCheckPersonBinding
import com.kusitms.hdmedi.feature.selfcheck.ui.databinding.RowButtonBinding


class SelfCheckPersonFragment : Fragment() {

    lateinit var fragmentSelfCheckPersonBinding: FragmentSelfCheckPersonBinding

    lateinit var navController: NavController

    lateinit var viewModel: SelfCheckQuestionViewModel

    var characterList = mutableListOf<String>()
    var accessToken = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentSelfCheckPersonBinding = FragmentSelfCheckPersonBinding.inflate(inflater)

        viewModel = ViewModelProvider(requireActivity())[SelfCheckQuestionViewModel::class.java]
        viewModel.run {
            characterNameList.observe(requireActivity()) {
                characterList = it
                Log.d("##", "characterlist : $characterList")
                fragmentSelfCheckPersonBinding.recyclerViewSelfCheckPerson.adapter?.notifyDataSetChanged()
            }
            token.observe(requireActivity()) {
                accessToken = it
                Log.d("##","token value : $accessToken")
            }
        }

//        viewModel.getToken()
//        Log.d("##", "access : ${accessToken.toString()}")
//        viewModel.getQuestion(accessToken)


        fragmentSelfCheckPersonBinding.run {
            toolbarSelfCheckPerson.run {
                title = "자가진단"
            }

            recyclerViewSelfCheckPerson.run {
                adapter = RecyclerViewAdapter()

                layoutManager = LinearLayoutManager(requireContext())
            }
        }
        return fragmentSelfCheckPersonBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
    }

    inner class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolderClass>() {
        inner class ViewHolderClass(rowBinding: RowButtonBinding) :
            RecyclerView.ViewHolder(rowBinding.root) {

            val buttonPerson: Button


            init {
                buttonPerson = rowBinding.buttonSelfCheckPersonRow

                buttonPerson.setOnClickListener {
                    val bundle = bundleOf("character" to rowBinding.buttonSelfCheckPersonRow.text.toString())
                    navController.navigate(R.id.action_selfCheckPersonFragment_to_selfCheckQuestionFragment, bundle)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            val rowPostListBinding = RowButtonBinding.inflate(layoutInflater)
            val viewHolder = ViewHolderClass(rowPostListBinding)

            rowPostListBinding.root.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            return viewHolder
        }

        override fun getItemCount(): Int {
            Log.d("##", "character list size : ${characterList.size}")
            return characterList.size
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            holder.buttonPerson.text = characterList.get(position).toString()
        }
    }
}