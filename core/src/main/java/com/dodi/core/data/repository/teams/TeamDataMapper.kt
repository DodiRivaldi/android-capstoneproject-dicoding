package com.dodi.core.data.repository.teams

import com.dodi.core.data.model.TeamModel
import com.dodi.core.data.repository.teams.local.FavoriteEntity
import com.dodi.core.data.repository.teams.local.TeamEntity
import com.dodi.core.data.repository.teams.remote.TeamResponse

object TeamDataMapper {

    fun mapTeamResponseToEntity(item : List<TeamResponse.TeamList>):
            List<TeamEntity>{
        val itemList = ArrayList<TeamEntity>()
        item.map {
            val team = TeamEntity(
                id = it.idTeam,
                strTeam = it.strTeam,
                strTeamShort = it.strTeamShort,
                strAlternate = it.strAlternate,
                strStadium = it.strStadium,
                strKeywords = it.strKeywords,
                strStadiumThumb = it.strStadiumThumb,
                strStadiumDescription = it.strStadiumDescription,
                strStadiumLocation = it.strStadiumLocation,
                intStadiumCapacity = it.intStadiumCapacity,
                strDescriptionEN = it.strDescriptionEN,
                strTeamBadge = it.strTeamBadge,
                strTeamJersey = it.strTeamJersey,
                strTeamLogo = it.strTeamLogo,
                strTeamBanner = it.strTeamBanner
            )
            itemList.add(team)
        }
        return itemList
    }

    fun mapTeamEntityToDomain(item : List<TeamEntity>): List<TeamModel> =
        item.map {
            TeamModel(
                id = it.id,
                strTeam = it.strTeam,
                strTeamShort = it.strTeamShort,
                strAlternate = it.strAlternate,
                strStadium = it.strStadium,
                strKeywords = it.strKeywords,
                strStadiumThumb = it.strStadiumThumb,
                strStadiumDescription = it.strStadiumDescription,
                strStadiumLocation = it.strStadiumLocation,
                intStadiumCapacity = it.intStadiumCapacity,
                strDescriptionEN = it.strDescriptionEN,
                strTeamBadge = it.strTeamBadge,
                strTeamJersey = it.strTeamJersey,
                strTeamLogo = it.strTeamLogo,
                strTeamBanner = it.strTeamBanner
            )
        }

    fun mapDomainToFavoriteEntity(it: TeamModel) = FavoriteEntity(
        id = it.id,
        strTeam = it.strTeam,
        strTeamShort = it.strTeamShort,
        strAlternate = it.strAlternate,
        strStadium = it.strStadium,
        strKeywords = it.strKeywords,
        strStadiumThumb = it.strStadiumThumb,
        strStadiumDescription = it.strStadiumDescription,
        strStadiumLocation = it.strStadiumLocation,
        intStadiumCapacity = it.intStadiumCapacity,
        strDescriptionEN = it.strDescriptionEN,
        strTeamBadge = it.strTeamBadge,
        strTeamJersey = it.strTeamJersey,
        strTeamLogo = it.strTeamLogo,
        strTeamBanner = it.strTeamBanner
    )
}