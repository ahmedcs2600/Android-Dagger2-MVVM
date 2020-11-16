package com.app.daggermvvm.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.daggermvvm.models.State
import com.app.daggermvvm.models.responses.MoviesListResponse
import com.app.daggermvvm.network.datasource.home.HomeRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel (private val mRepository: HomeRepository) : ViewModel() {

    private val _moviesList = MutableLiveData<State<MoviesListResponse>>()
    val moviesList: LiveData<State<MoviesListResponse>> get() = _moviesList

    init {
        fetchMovies()
    }

   private fun fetchMovies() {
        viewModelScope.launch {
            mRepository.fetchMovies().collect {
                _moviesList.value = it
            }
        }
    }
}