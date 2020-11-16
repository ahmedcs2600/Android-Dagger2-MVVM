package com.app.daggermvvm.network.datasource.home

import com.app.daggermvvm.models.State
import com.app.daggermvvm.models.responses.MoviesListResponse
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun fetchMovies() : Flow<State<MoviesListResponse>>
}