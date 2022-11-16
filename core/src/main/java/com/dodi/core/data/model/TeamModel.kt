package com.dodi.core.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TeamModel(
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
) : Parcelable