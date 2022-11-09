package com.dodi.core.data.repository

import com.dodi.core.data.repository.teams.local.TeamDao
import com.dodi.core.data.repository.teams.local.TeamEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val teamDao: TeamDao) {
    fun getData(): Flow<List<TeamEntity>> = teamDao.get()
    suspend fun insert(item : List<TeamEntity>) = teamDao.insert(item)
}