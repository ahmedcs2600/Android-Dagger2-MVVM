package com.app.daggermvvm.network

import com.app.daggermvvm.models.responses.ActorsListResponse
import com.app.daggermvvm.models.responses.MoviesListResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {
    @GET("discover/movie")
    suspend fun getNowPlaying(
        @Query("api_key") apiKey: String
    ): Response<MoviesListResponse>

    @GET("movie/{movie_id}/credits")
    suspend fun getActorsList(
        @Path("movie_id") movieId: String,
        @Query("api_key") apiKey: String
    ): Response<ActorsListResponse>
}