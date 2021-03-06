package com.app.daggermvvm.network.datasource

import androidx.annotation.MainThread
import com.app.daggermvvm.models.State
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response

/**
 * A repository which provides resource from local database as well as remote end point.
 *
 * [RESULT] represents the type for database.
 * [REQUEST] represents the type for network.
 */
@ExperimentalCoroutinesApi
abstract class NetworkBoundRepository<RESULT> {

    fun asFlow() = flow<State<RESULT>> {

        // Emit Loading State
        emit(State.loading())

        // Fetch latest data from remote
        val apiResponse = fetchFromRemote()

        // Parse body
        val remoteData = apiResponse.body()

        // Check for response validation
        if (apiResponse.isSuccessful && remoteData != null) {
            emit(State.success(remoteData))
        } else {
            // Something went wrong! Emit Error state.
            emit(State.error(apiResponse.message()))
        }
    }.catch { e ->
        // Exception occurred! Emit error
        emit(State.error("Network error! Can't get latest posts."))
        e.printStackTrace()
    }

    /**
     * Fetches [Response] from the remote end point.
     */
    @MainThread
    protected abstract suspend fun fetchFromRemote(): Response<RESULT>
}
