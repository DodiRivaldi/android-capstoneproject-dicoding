package com.dodi.core.di

import android.content.Context
import androidx.room.Room
import com.dodi.core.data.database.AppDatabase
import com.dodi.core.data.repository.teams.local.FavoriteDao
import com.dodi.core.data.repository.teams.local.TeamDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(context: Context): AppDatabase = Room.databaseBuilder(
        context, AppDatabase::class.java, "epl.db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideTeamDao(database: AppDatabase): TeamDao = database.teamDao()

    @Provides
    fun provideFavoriteDao(database: AppDatabase): FavoriteDao = database.favoriteDao()
}