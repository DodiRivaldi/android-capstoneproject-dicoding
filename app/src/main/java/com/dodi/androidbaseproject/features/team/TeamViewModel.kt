package com.dodi.androidbaseproject.features.team

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dodi.core.data.domain.team.TeamUseCase
import com.dodi.core.data.model.TeamModel
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class TeamViewModel @Inject constructor(private val useCase: TeamUseCase?) : ViewModel() {

    fun getTeamData() = useCase?.getTeams()?.asLiveData()
    fun isFavorite(teamModel: TeamModel) = useCase?.isFavorite(teamModel)?.asLiveData()
    fun insertFavorite(teamModel: TeamModel, state : Boolean) = useCase?.insertFavorite(teamModel, state)
    fun searchTeams(query : String) = useCase?.searchTeams(query)?.asLiveData()
}