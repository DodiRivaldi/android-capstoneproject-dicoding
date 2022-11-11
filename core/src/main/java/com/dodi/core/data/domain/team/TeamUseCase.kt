package com.dodi.core.data.domain.team

import com.dodi.core.data.Resource
import com.dodi.core.data.model.TeamModel
import kotlinx.coroutines.flow.Flow

interface TeamUseCase {
    fun getData(): Flow<Resource<List<TeamModel>>>
    fun isFavorite(teamModel: TeamModel): Flow<Boolean>
}