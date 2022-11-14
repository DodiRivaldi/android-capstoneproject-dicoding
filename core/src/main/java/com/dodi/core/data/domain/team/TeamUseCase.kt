package com.dodi.core.data.domain.team

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.dodi.core.data.Resource
import com.dodi.core.data.model.TeamModel
import kotlinx.coroutines.flow.Flow

interface TeamUseCase {
    fun getTeams(): Flow<Resource<List<TeamModel>>>
    fun isFavorite(teamModel: TeamModel): Flow<Boolean>
    fun getFavorite(): LiveData<PagedList<TeamModel>>
    fun insertFavorite(teamModel: TeamModel, state : Boolean)
}