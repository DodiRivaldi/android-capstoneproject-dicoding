package com.dodi.core.data

import android.util.Log
import com.dodi.core.data.network.ApiResponse
import kotlinx.coroutines.flow.*

abstract class NetworkBoundResource<ResultType, RequestType> {

    private var result : Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())
        val dbSource = loadFromDb().first()
        if (shouldFetch(dbSource)){
            Log.d("CHECK","1")
            emit(Resource.Loading())
            when(val apiResponse = createCall().first()){
                is ApiResponse.Success -> {
                    Log.d("CHECK","2")

                    saveCallResult(apiResponse.data)
                    emitAll(loadFromDb().map {
                        Resource.Success(
                            it
                        )
                    })
                }
                is ApiResponse.Empty -> {
                    Log.d("CHECK","3")

                    emitAll(loadFromDb().map {
                        Resource.Success(
                            it
                        )
                    })
                }
                is ApiResponse.Error -> {
                    Log.d("CHECK","4")

                    onFetchFailed()
                    emit(Resource.Error<ResultType>(
                        apiResponse.message
                    ))
                }
            }
        }
        else{
            Log.d("CHECK","5")

            emitAll(loadFromDb().map {
                Resource.Success(it)
            })
        }
    }

    protected open fun onFetchFailed(){}

    protected abstract fun loadFromDb(): Flow<ResultType>

    protected abstract fun shouldFetch(data : ResultType?): Boolean

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    protected abstract suspend fun saveCallResult(data: RequestType)

    fun asFlow(): Flow<Resource<ResultType>> = result
}