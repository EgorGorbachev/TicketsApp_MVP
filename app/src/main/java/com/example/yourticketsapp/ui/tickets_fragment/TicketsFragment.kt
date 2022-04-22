package com.example.yourticketsapp.ui.tickets_fragment

import android.os.Bundle
import android.view.View
import com.example.yourticketsapp.R
import com.example.yourticketsapp.core.BaseFragment
import com.example.yourticketsapp.databinding.FragmentDiscoverBinding
import com.example.yourticketsapp.databinding.FragmentTicketsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TicketsFragment : BaseFragment(R.layout.fragment_tickets) {

    private var _binding:FragmentTicketsBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentTicketsBinding.bind(view)

    }

}