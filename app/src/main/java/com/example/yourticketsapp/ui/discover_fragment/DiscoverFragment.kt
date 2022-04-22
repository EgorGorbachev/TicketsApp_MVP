package com.example.yourticketsapp.ui.discover_fragment

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.yourticketsapp.R
import com.example.yourticketsapp.contract.MainContract
import com.example.yourticketsapp.core.BaseFragment
import com.example.yourticketsapp.data.models.ClosePerformancesModel
import com.example.yourticketsapp.data.models.PopularPerformanceModel
import com.example.yourticketsapp.data.models.TheatersModel
import com.example.yourticketsapp.databinding.FragmentDiscoverBinding
import com.example.yourticketsapp.presenters.discover_fragment_presenter.DiscoverFragmentPresenter
import dagger.hilt.android.AndroidEntryPoint
import java.io.InputStream
import javax.inject.Inject

@AndroidEntryPoint
class DiscoverFragment : BaseFragment(R.layout.fragment_discover),
    MainContract.MainView,
    PopularPerformanceRecyclerAdapter.OnItemClickListener,
    ClosePerformancesRecyclerAdapter.OnItemClickListener,
    TheatersRecyclerAdapter.OnItemClickListener {

    @Inject
    lateinit var presenter: MainContract.PresenterFragment

    private var _binding: FragmentDiscoverBinding? = null
    private val binding get() = _binding!!

    private lateinit var popularAdapter: PopularPerformanceRecyclerAdapter
    private lateinit var popularRecyclerView: RecyclerView

    lateinit var closeAdapter: ClosePerformancesRecyclerAdapter
    private lateinit var closeRecyclerView: RecyclerView

    lateinit var theatersAdapter: TheatersRecyclerAdapter
    private lateinit var theatersRecyclerView: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentDiscoverBinding.bind(view)

        presenter = DiscoverFragmentPresenter(this)
        presenter.attachView(this, view)

        popularAdapter = PopularPerformanceRecyclerAdapter(this)
        popularRecyclerView = binding.discoverPopularPerformancesRecycler
        val popularLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        popularRecyclerView.layoutManager = popularLayoutManager
        popularRecyclerView.adapter = popularAdapter

        closeAdapter = ClosePerformancesRecyclerAdapter(this)
        closeRecyclerView = binding.discoverClosePerformancesRecycler
        val closeLayoutManager =
            GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        closeRecyclerView.layoutManager = closeLayoutManager
        closeRecyclerView.adapter = closeAdapter
        closeRecyclerView.setHasFixedSize(true)

        theatersAdapter = TheatersRecyclerAdapter(this)
        theatersRecyclerView = binding.discoverTheatersRecycler
        val theatersLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        theatersRecyclerView.layoutManager = theatersLayoutManager
        theatersRecyclerView.adapter = theatersAdapter

        closeLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position == closeAdapter.itemCount) 2
                else 1
            }
        }

        val popularList = (presenter as DiscoverFragmentPresenter).createPopularList()
        popularAdapter.submitList(popularList)


        val closeList = (presenter as DiscoverFragmentPresenter).createCloseList()
        closeAdapter.submitList(closeList)

        val theatersList = (presenter as DiscoverFragmentPresenter).createTheatersList()
        theatersAdapter.submitList(theatersList)
    }


    override fun onClickItem(item: PopularPerformanceModel) {
        val action =
            DiscoverFragmentDirections.actionDiscoverFragmentToPopularPerfDetailsFragment(item)
        findNavController().navigate(action)
        toast("lol")
    }

    override fun onClickItem(item: ClosePerformancesModel) {
        Log.v(TAG, "lol")
    }

    override fun onClickItem(theatersModel: TheatersModel) {
        Log.v(TAG, "lol")
    }

    override fun showToast(message: String) {
        toast(message)
    }
}