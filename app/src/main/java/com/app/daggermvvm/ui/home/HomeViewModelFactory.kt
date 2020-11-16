package com.app.daggermvvm.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.daggermvvm.network.datasource.home.HomeRepository
import javax.inject.Inject

class HomeViewModelFactory @Inject constructor(private val mHomeRepository: HomeRepository) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(mHomeRepository) as T
    }
}