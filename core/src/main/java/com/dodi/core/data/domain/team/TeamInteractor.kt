package com.dodi.core.data.domain.team

import com.dodi.core.data.Resource
import com.dodi.core.data.model.TeamModel
import com.dodi.core.data.repository.teams.ITeamRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TeamInteractor @Inject constructor(private val repository: ITeamRepository): TeamUseCase {
    override fun getData(): Flow<Resource<List<TeamModel>>> = repository.getData()
    override fun isFavorite(teamModel: TeamModel): Flow<Boolean> = repository.isFavorite(teamModel)
}