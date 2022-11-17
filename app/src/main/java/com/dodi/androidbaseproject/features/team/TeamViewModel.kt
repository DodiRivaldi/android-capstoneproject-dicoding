package com.dodi.androidbaseproject.features.team

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    //fun searchTeams(query: String) = useCase?.searchTeams(query)?.asLiveData()

    private val _search = MutableLiveData<List<TeamModel>>()
    val searchLiveData : LiveData<List<TeamModel>> get() = _search

    fun search(query : String){
        useCase?.searchTeams(query)?.asLiveData().let {
            Log.d("HASILVIEWMODEL",it?.value?.get(0)?.strTeam.toString())
            //_search.postValue(it?.value)
        }
    }

    fun customSearch(query: String): LiveData<List<TeamModel>>{
        return useCase?.searchTeams(query)?.asLiveData() as LiveData<List<TeamModel>>
    }
}