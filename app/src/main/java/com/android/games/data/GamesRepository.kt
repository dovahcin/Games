package com.android.games.data

import com.android.games.data.network.API_KEY
import com.android.games.data.network.ApiServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GamesRepository(private val api: ApiServices) {

    fun getGames(page: Int) = flow {
        val games = api.getGamesList(API_KEY, page)
        emit(games)
    }.flowOn(Dispatchers.IO)

}