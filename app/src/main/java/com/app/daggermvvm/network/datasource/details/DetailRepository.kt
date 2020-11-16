package com.app.daggermvvm.network.datasource.details

import com.app.daggermvvm.models.State
import com.app.daggermvvm.models.responses.ActorsListResponse
import kotlinx.coroutines.flow.Flow

interface DetailRepository {
    fun fetchActorsList(movieId : Int) : Flow<State<ActorsListResponse>>
}