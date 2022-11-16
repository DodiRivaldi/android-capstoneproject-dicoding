package com.dodi.androidbaseproject.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dodi.androidbaseproject.features.ViewModelFactory
import com.dodi.androidbaseproject.features.detail.DetailViewModel
import com.dodi.androidbaseproject.features.team.TeamViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(TeamViewModel::class)
    abstract fun bindTeamViewModel(viewModel: TeamViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun bindDetailViewModel(viewModel: DetailViewModel): ViewModel
}