package com.example.yourticketsapp.ui.popular_perf_details_fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.yourticketsapp.R
import com.example.yourticketsapp.core.BaseFragment
import com.example.yourticketsapp.databinding.FragmentPopularPerfDetailsBinding


class PopularPerfDetailsFragment : BaseFragment(R.layout.fragment_popular_perf_details) {

    private var _binding:FragmentPopularPerfDetailsBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<PopularPerfDetailsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentPopularPerfDetailsBinding.bind(view)

        Glide.with(view).load(args.popularDetailsInfo.img).into(binding.popDetailsImg)
        binding.popDetailsTitle.text = args.popularDetailsInfo.title
        binding.ratingBar.rating = args.popularDetailsInfo.rating
        binding.popDetailsCategory.text = args.popularDetailsInfo.category
        binding.popDetailsDescription.text = args.popularDetailsInfo.description
    }

}