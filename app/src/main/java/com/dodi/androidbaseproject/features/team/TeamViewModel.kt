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

    val queryChannel = BroadcastChannel<String>(Channel.CONFLATED)
    val search = queryChannel.asFlow()
        .debounce(1000)
        .distinctUntilChanged()
        .filter {
            it.trim().isNotEmpty()
        }
        .mapLatest {
            //useCase?.searchMovies(it)
        }
        .asLiveData()
}