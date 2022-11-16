package com.dodi.core.data.repository.teams.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TeamDao {
    @Query("select * from team")
    fun get(): Flow<List<TeamEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: List<TeamEntity>)

    @Delete
    fun delete(item: TeamEntity)

    @Query("SELECT * FROM team  WHERE strTeam LIKE '%' || :query || '%'")
    fun searchTeam(query: String): Flow<List<TeamEntity>>
}