package com.dodi.androidbaseproject.features.team

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dodi.core.data.domain.team.TeamUseCase
import com.dodi.core.data.model.TeamModel
import javax.inject.Inject

class TeamViewModel @Inject constructor(private val useCase: TeamUseCase?) : ViewModel() {

    fun getTeamData() = useCase?.getTeams()?.asLiveData()
    fun isFavorite(teamModel: TeamModel) = useCase?.isFavorite(teamModel)?.asLiveData()
    fun insertFavorite(teamModel: TeamModel, state: Boolean) =
        useCase?.insertFavorite(teamModel, state)

    fun customSearch(query: String): LiveData<List<TeamModel>>{
        return useCase?.searchTeams(query)?.asLiveData() as LiveData<List<TeamModel>>
    }
}