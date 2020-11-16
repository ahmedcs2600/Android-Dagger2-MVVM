package com.app.daggermvvm.ui.details

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.daggermvvm.R
import com.app.daggermvvm.base.AppFragment
import com.app.daggermvvm.databinding.FragmentDetailsBinding
import com.app.daggermvvm.exceptions.NoBundleArgumentFound
import com.app.daggermvvm.ktx.showToast
import com.app.daggermvvm.models.State
import java.lang.IllegalStateException
import javax.inject.Inject
import kotlin.contracts.ExperimentalContracts


class DetailsFragment : AppFragment<FragmentDetailsBinding>(R.layout.fragment_details) {

    private val arguments: DetailsFragmentArgs by navArgs()

    @Inject
    lateinit var mViewModelFactory: DetailsViewModelFactory

    private val mDetailsViewModel by viewModels<DetailsViewModel> { mViewModelFactory }

    private lateinit var mAdapter: ActorsRecyclerAdapter

    @ExperimentalContracts
    override fun initializeComponents() {
        val item = arguments.item ?: throw NoBundleArgumentFound("Movie item argument is Null")
        appComponent.detailsComponent().create(item).inject(this)
        binding.viewModel = mDetailsViewModel
        setUpAdapter()
        bindViewModel()
    }

    private fun setUpAdapter() {
        with(binding.recyclerActors) {
            adapter = ActorsRecyclerAdapter().also { mAdapter = it }
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun bindViewModel() {
        mDetailsViewModel.actorList.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is State.Loading -> {
                    binding.isLoading = true
                }
                is State.Error -> {
                    binding.isLoading = false
                    showToast(state.message)
                }
                is State.Success -> {
                    binding.isLoading = false
                    val data = state.data?.actorsList ?: throw IllegalStateException("Actors Data is Null")
                    mAdapter.updateList(data)
                }
            }
        })
    }

}