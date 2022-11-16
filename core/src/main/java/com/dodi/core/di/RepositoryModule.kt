package com.dodi.core.di

import com.dodi.core.data.repository.teams.ITeamRepository
import com.dodi.core.data.repository.teams.TeamRepository
import dagger.Binds
import dagger.Module

@Module(includes = [NetworkModule::class, DatabaseModule::class])
abstract class RepositoryModule {
    @Suppress("unused")
    @Binds
    abstract fun provideTeamRepository(repositoryImpl: TeamRepository): ITeamRepository
}