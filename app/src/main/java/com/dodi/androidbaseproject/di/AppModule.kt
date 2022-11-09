package com.dodi.androidbaseproject.di

import com.dodi.core.data.domain.team.TeamInteractor
import com.dodi.core.data.domain.team.TeamUseCase
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {
    @Suppress("unused")
    @Binds
    abstract fun provideTeamUseCase(teamInteractor: TeamInteractor): TeamUseCase
}