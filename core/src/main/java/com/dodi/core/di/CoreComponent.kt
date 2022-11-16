package com.dodi.core.di

import android.content.Context
import com.dodi.core.data.repository.teams.ITeamRepository
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class])
interface CoreComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): CoreComponent
    }

    fun provideTeamRepository(): ITeamRepository
}