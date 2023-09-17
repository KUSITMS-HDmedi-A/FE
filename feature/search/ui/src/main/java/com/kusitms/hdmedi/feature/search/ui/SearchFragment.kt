package com.kusitms.hdmedi.feature.search.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kusitms.hdmedi.feature.search.ui.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    lateinit var fragmentSearchBinding: FragmentSearchBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentSearchBinding = FragmentSearchBinding.inflate(inflater)

        fragmentSearchBinding.run {
            toolbarSearch.run {
                title = "약검색"
            }
        }
        return fragmentSearchBinding.root
    }
}