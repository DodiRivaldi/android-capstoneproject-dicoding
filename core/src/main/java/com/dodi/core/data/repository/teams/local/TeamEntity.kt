package com.dodi.core.data.repository.teams.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "team")
data class TeamEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val strTeam: String?,
    val strTeamShort: String?,
    val strAlternate: String?,
    val strStadium: String?,
    val strKeywords: String?,
    val strStadiumThumb: String?,
    val strStadiumDescription: String?,
    val strStadiumLocation: String?,
    val intStadiumCapacity: String?,
    val strDescriptionEN: String?,
    val strTeamBadge: String?,
    val strTeamJersey: String?,
    val strTeamLogo: String?,
    val strTeamBanner: String?
)