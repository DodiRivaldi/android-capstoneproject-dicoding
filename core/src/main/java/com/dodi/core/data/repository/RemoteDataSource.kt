package com.dodi.core.data.repository

import com.dodi.core.data.network.ApiResponse
import com.dodi.core.data.network.ApiService
import com.dodi.core.data.repository.teams.remote.TeamResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getTeams(): Flow<ApiResponse<List<TeamResponse.TeamList>>> {
        return flow {
            try {
                val response = apiService.getTeams()
                val dataArray = response.teamList

                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.teamList))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.localizedMessage))
            }
        }.flowOn(Dispatchers.IO)
    }
}