package com.dodi.core.data.repository

import androidx.paging.DataSource
import com.dodi.core.data.repository.teams.local.FavoriteDao
import com.dodi.core.data.repository.teams.local.FavoriteEntity
import com.dodi.core.data.repository.teams.local.TeamDao
import com.dodi.core.data.repository.teams.local.TeamEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val teamDao: TeamDao, private val favoriteDao: FavoriteDao) {
    fun getTeams(): Flow<List<TeamEntity>> = teamDao.get()
    suspend fun insertTeam(item : List<TeamEntity>) = teamDao.insert(item)
    suspend fun isFavorite(item : FavoriteEntity): Boolean =  favoriteDao.isExist(item.id)
    fun insertFavoriteTeam(team : FavoriteEntity, state : Boolean){
        if (!state) favoriteDao.insert(team)
        else favoriteDao.delete(team)
    }
    fun getFavoriteTeam(): DataSource.Factory<Int, FavoriteEntity> = favoriteDao.get()
    fun searchTeams(query : String): Flow<List<TeamEntity>> = teamDao.searchTeam(query)
}