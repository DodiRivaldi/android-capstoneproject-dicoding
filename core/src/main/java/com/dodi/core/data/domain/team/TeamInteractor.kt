package com.dodi.core.data.domain.team

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.dodi.core.data.Resource
import com.dodi.core.data.model.TeamModel
import com.dodi.core.data.repository.teams.ITeamRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TeamInteractor @Inject constructor(private val repository: ITeamRepository) : TeamUseCase {
    override fun getTeams(): Flow<Resource<List<TeamModel>>> = repository.getData()
    override fun isFavorite(teamModel: TeamModel): Flow<Boolean> = repository.isFavorite(teamModel)
    override fun getFavorite(): LiveData<PagedList<TeamModel>> = repository.getFavorite()
    override fun insertFavorite(teamModel: TeamModel, state: Boolean) =
        repository.insertFavorite(teamModel, state)

    override fun searchTeams(query: String): Flow<List<TeamModel>> = repository.searchTeams(query)
}