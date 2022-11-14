package com.dodi.core.data.repository.teams.local

import androidx.paging.DataSource
import androidx.room.*

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: FavoriteEntity)

    @Delete
    fun delete(data: FavoriteEntity)

    @Query("select * from favorite_team where strTeam != ''")
    fun get(): DataSource.Factory<Int, FavoriteEntity>


    @Query("SELECT EXISTS (SELECT 1 FROM favorite_team WHERE id = :id)")
    suspend fun isExist(id: String): Boolean
}