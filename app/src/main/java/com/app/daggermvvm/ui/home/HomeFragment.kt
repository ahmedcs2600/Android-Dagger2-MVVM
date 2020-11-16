package com.app.daggermvvm.ui.home

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.app.daggermvvm.R
import com.app.daggermvvm.base.AppFragment
import com.app.daggermvvm.databinding.FragmentHomeBinding
import com.app.daggermvvm.ktx.showToast
import com.app.daggermvvm.models.State
import javax.inject.Inject

class HomeFragment : AppFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    @Inject
    lateinit var mViewModelFactory : HomeViewModelFactory

    private val mHomeViewModel by viewModels<HomeViewModel> { mViewModelFactory }

    private lateinit var mAdapter: HomeAdapter

    override fun initializeComponents() {
        appComponent.homeComponent().create().inject(this)
        setUpAdapter()
        bindViewModel()
    }


    private fun setUpAdapter() {
        with(binding.recyclerMoviesList) {
            adapter = HomeAdapter { movieItem ->
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailsFragment(movieItem))
            }.also {
                mAdapter = it
            }
            layoutManager = GridLayoutManager(requireContext(), 2)
            setHasFixedSize(true)
        }
    }

    private fun bindViewModel() {
        mHomeViewModel.moviesList.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is State.Loading -> binding.isLoading = true
                is State.Error -> {
                    binding.isLoading = false
                    showToast(state.message)
                }
                is State.Success -> {
                    binding.isLoading = false
                    state.data?.results?.let { result ->
                        mAdapter.updateList(result)
                    }
                }
            }
        })
    }
}