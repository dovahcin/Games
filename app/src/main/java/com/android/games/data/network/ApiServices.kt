package com.android.games.data.network

import com.android.games.domain.GameDetails
import com.android.games.domain.Games
import com.android.games.domain.ScreenShots
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val API_KEY = "cf02e1d358914f23b368744a28900d2a"

interface ApiServices {

    @GET("games")
    suspend fun getGamesList(
        @Query("key") apiKey: String,
        @Query("page") page: Int
    ): Games

    @GET("games")
    suspend fun getSearchResults(
        @Query("key") apiKey: String,
        @Query("search") searchQuery: String
    ): Games

    @GET("games/{id}")
    suspend fun getGamesDetails(
        @Path("id") gameId: Int,
        @Query("key") apiKey: String
    ): GameDetails

    @GET("games/{game_pk}/screenshots")
    suspend fun getScreenShots(
        @Path("game_pk") gameId: Int,
        @Query("key") apiKey: String
    ): ScreenShots


}