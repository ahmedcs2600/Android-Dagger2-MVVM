package com.app.daggermvvm.network.datasource.home

import com.app.daggermvvm.global.API_KEY
import com.app.daggermvvm.models.State
import com.app.daggermvvm.models.responses.MoviesListResponse
import com.app.daggermvvm.network.MovieApiService
import com.app.daggermvvm.network.datasource.NetworkBoundRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class HomeRepositoryImp @Inject constructor(private val mMovieApiService : MovieApiService): HomeRepository {

    @ExperimentalCoroutinesApi
    override fun fetchMovies(): Flow<State<MoviesListResponse>> {
        return object : NetworkBoundRepository<MoviesListResponse>(){
            override suspend fun fetchFromRemote(): Response<MoviesListResponse> = mMovieApiService.getNowPlaying(API_KEY)
        }.asFlow()
    }
}