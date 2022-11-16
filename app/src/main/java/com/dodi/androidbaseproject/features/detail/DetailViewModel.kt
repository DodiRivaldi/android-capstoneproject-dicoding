package com.dodi.androidbaseproject.features.detail

import androidx.lifecycle.*
import com.dodi.core.data.domain.team.TeamUseCase
import com.dodi.core.data.model.TeamModel
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val useCase: TeamUseCase?) : ViewModel() {
    private val _item = MutableLiveData<TeamModel>()
    val item: LiveData<TeamModel> = _item
    val isFavorite: LiveData<Boolean> = Transformations.switchMap(_item) {
        useCase?.isFavorite(it)?.asLiveData()
    }

    fun setSelected(teamModel: TeamModel) {
        _item.postValue(teamModel)
    }

    fun insertFavorite(state: Boolean) {
        _item.value?.let {
            useCase?.insertFavorite(it, state)
            _item.postValue(it)
        }
    }

}