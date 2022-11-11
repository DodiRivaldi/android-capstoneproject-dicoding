package com.dodi.androidbaseproject.features.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dodi.core.data.domain.team.TeamUseCase
import javax.inject.Inject

class MainViewModel @Inject constructor(private val useCase: TeamUseCase?) : ViewModel() {

    fun getTeamData() = useCase?.getData()?.asLiveData()


}