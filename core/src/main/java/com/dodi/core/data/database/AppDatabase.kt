package com.dodi.core.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dodi.core.data.repository.teams.local.FavoriteDao
import com.dodi.core.data.repository.teams.local.FavoriteEntity
import com.dodi.core.data.repository.teams.local.TeamDao
import com.dodi.core.data.repository.teams.local.TeamEntity

@Database(
    entities = [TeamEntity::class, FavoriteEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun teamDao() : TeamDao
    abstract fun favoriteDao(): FavoriteDao
}