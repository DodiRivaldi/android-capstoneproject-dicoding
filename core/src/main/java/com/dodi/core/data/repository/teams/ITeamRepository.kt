package com.dodi.core.data.repository.teams

import com.dodi.core.data.Resource
import com.dodi.core.data.model.TeamModel
import kotlinx.coroutines.flow.Flow

interface ITeamRepository {
    fun getData(): Flow<Resource<List<TeamModel>>>

}