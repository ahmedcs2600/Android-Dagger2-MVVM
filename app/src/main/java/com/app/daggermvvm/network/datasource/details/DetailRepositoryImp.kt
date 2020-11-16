package com.app.daggermvvm.network.datasource.details

import com.app.daggermvvm.global.API_KEY
import com.app.daggermvvm.models.State
import com.app.daggermvvm.models.responses.ActorsListResponse
import com.app.daggermvvm.network.MovieApiService
import com.app.daggermvvm.network.datasource.NetworkBoundRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class DetailRepositoryImp @Inject constructor(private val mMovieApiService: MovieApiService) :
    DetailRepository {

    @ExperimentalCoroutinesApi
    override fun fetchActorsList(movieId: Int): Flow<State<ActorsListResponse>> {
        return object : NetworkBoundRepository<ActorsListResponse>() {
            override suspend fun fetchFromRemote(): Response<ActorsListResponse> =
                mMovieApiService.getActorsList(movieId.toString(), API_KEY)
        }.asFlow()
    }
}