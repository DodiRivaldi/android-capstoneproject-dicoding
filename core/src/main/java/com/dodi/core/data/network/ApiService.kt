package com.dodi.core.data.network

import com.dodi.core.data.repository.teams.remote.TeamResponse
import retrofit2.http.GET

interface ApiService {

    companion object {
        private const val TEAMS = "search_all_teams.php?l=English%20Premier%20League"
    }

    @GET(TEAMS)
    suspend fun getTeams(): TeamResponse


}