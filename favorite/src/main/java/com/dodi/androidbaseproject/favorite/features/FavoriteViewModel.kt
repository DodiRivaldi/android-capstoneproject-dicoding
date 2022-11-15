package com.dodi.androidbaseproject.favorite.features

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dodi.core.data.domain.team.TeamUseCase
import com.dodi.core.data.model.TeamModel
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(private val useCase: TeamUseCase?): ViewModel() {
    val data = useCase?.getFavorite()

    fun isFavorite(teamModel: TeamModel) = useCase?.isFavorite(teamModel)?.asLiveData()
    fun insertFavorite(teamModel: TeamModel, state : Boolean) = useCase?.insertFavorite(teamModel, state)
}