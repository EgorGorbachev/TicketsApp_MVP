package com.example.yourticketsapp.ui.account_fragment

import android.os.Bundle
import android.view.View
import com.example.yourticketsapp.R
import com.example.yourticketsapp.core.BaseFragment
import com.example.yourticketsapp.databinding.FragmentAccountBinding
import dagger.hilt.android.AndroidEntryPoint


class AccountFragment : BaseFragment(R.layout.fragment_account) {

    private var _binding:FragmentAccountBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentAccountBinding.bind(view)
    }

}