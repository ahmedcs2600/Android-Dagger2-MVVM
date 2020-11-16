package com.app.daggermvvm.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.daggermvvm.models.responses.MovieModel
import com.app.daggermvvm.network.datasource.details.DetailRepository
import javax.inject.Inject

class DetailsViewModelFactory @Inject constructor(private val mDetailRepository: DetailRepository,private val movieItem : MovieModel) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailsViewModel(mDetailRepository,movieItem) as T
    }
}