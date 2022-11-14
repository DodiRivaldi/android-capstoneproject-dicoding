package com.dodi.androidbaseproject.features.detail

import androidx.lifecycle.ViewModel
import com.dodi.core.data.domain.team.TeamUseCase
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val useCase: TeamUseCase?): ViewModel() {
}