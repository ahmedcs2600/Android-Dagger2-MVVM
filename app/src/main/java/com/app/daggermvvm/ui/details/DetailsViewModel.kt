package com.app.daggermvvm.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.daggermvvm.models.State
import com.app.daggermvvm.models.responses.ActorsListResponse
import com.app.daggermvvm.models.responses.MovieModel
import com.app.daggermvvm.network.datasource.details.DetailRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val mDetailRepository: DetailRepository,
    private val movieItem: MovieModel
) : ViewModel() {

    private val _actorsList = MutableLiveData<State<ActorsListResponse>>()
    val actorList: LiveData<State<ActorsListResponse>> get() = _actorsList

    private val _currentMovie = MutableLiveData<MovieModel>(movieItem)
    val currentMovie: LiveData<MovieModel> get() = _currentMovie


    init {
        fetchActor()
    }

    private fun fetchActor() {
        viewModelScope.launch {
            mDetailRepository.fetchActorsList(movieItem.id).collect { state ->
                _actorsList.value = state
            }
        }
    }

}