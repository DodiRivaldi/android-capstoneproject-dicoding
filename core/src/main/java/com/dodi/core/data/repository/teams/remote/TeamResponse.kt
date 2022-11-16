package com.dodi.core.data.repository.teams.remote

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class TeamResponse(
    @Expose @SerializedName("teams") val teamList: List<TeamList> = listOf()
) {

    @Parcelize
    data class TeamList(
        @Expose @SerializedName("idTeam") val idTeam: String,
        @Expose @SerializedName("strTeam") val strTeam: String? = "",
        @Expose @SerializedName("strTeamShort") val strTeamShort: String? = "",
        @Expose @SerializedName("strAlternate") val strAlternate: String? = "",
        @Expose @SerializedName("strStadium") val strStadium: String? = "",
        @Expose @SerializedName("strKeywords") val strKeywords: String? = "",
        @Expose @SerializedName("strStadiumThumb") val strStadiumThumb: String? = "",
        @Expose @SerializedName("strStadiumDescription") val strStadiumDescription: String? = "",
        @Expose @SerializedName("strStadiumLocation") val strStadiumLocation: String? = "",
        @Expose @SerializedName("intStadiumCapacity") val intStadiumCapacity: String? = "",
        @Expose @SerializedName("strDescriptionEN") val strDescriptionEN: String? = "",
        @Expose @SerializedName("strTeamBadge") val strTeamBadge: String? = "",
        @Expose @SerializedName("strTeamJersey") val strTeamJersey: String? = "",
        @Expose @SerializedName("strTeamLogo") val strTeamLogo: String? = "",
        @Expose @SerializedName("strTeamBanner") val strTeamBanner: String? = "",
    ) : Parcelable
}
