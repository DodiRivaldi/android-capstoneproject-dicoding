package com.dodi.core.data.repository.teams

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dodi.core.abstraction.utils.AppExecutors
import com.dodi.core.data.NetworkBoundResource
import com.dodi.core.data.Resource
import com.dodi.core.data.model.TeamModel
import com.dodi.core.data.network.ApiResponse
import com.dodi.core.data.repository.LocalDataSource
import com.dodi.core.data.repository.RemoteDataSource
import com.dodi.core.data.repository.teams.remote.TeamResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TeamRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : ITeamRepository {
    override fun getData(): Flow<Resource<List<TeamModel>>> =
        object : NetworkBoundResource<List<TeamModel>, List<TeamResponse.TeamList>>() {
            override fun loadFromDb(): Flow<List<TeamModel>> {
                return localDataSource.getTeams().map {
                    TeamDataMapper.mapTeamEntityToDomain(it)
                }
            }

            override fun shouldFetch(data: List<TeamModel>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<TeamResponse.TeamList>>> =
                remoteDataSource.getTeams()

            override suspend fun saveCallResult(data: List<TeamResponse.TeamList>) {
                val itemList = TeamDataMapper.mapTeamResponseToEntity(data)
                localDataSource.insertTeam(itemList)
            }

        }.asFlow()

    override fun isFavorite(teamModel: TeamModel): Flow<Boolean> {
        val teamEntity = TeamDataMapper.mapDomainToFavoriteEntity(teamModel)
        return flow {
            emit(localDataSource.isFavorite(teamEntity))
        }
    }

    override fun getFavorite(): LiveData<PagedList<TeamModel>> {
        val items = localDataSource.getFavoriteTeam().map {
            TeamDataMapper.mapFavoriteEntityToDomain(it)
        }

        val paged = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(2)
            .setPageSize(2)
            .build()

        return LivePagedListBuilder(items, paged).build()
    }

    override fun insertFavorite(teamModel: TeamModel, state: Boolean) {
        val item = TeamDataMapper.mapDomainToFavoriteEntity(teamModel)
        appExecutors.diskIO().execute { localDataSource.insertFavoriteTeam(item, state) }
    }

    override fun searchTeams(query: String): Flow<List<TeamModel>> = flow {
        localDataSource.searchTeams(query).map {
            TeamDataMapper.mapTeamEntityToDomain(it)
        }
    }
}