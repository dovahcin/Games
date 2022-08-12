package com.android.games.data

import android.util.Log
import com.android.games.data.network.API_KEY
import com.android.games.data.network.ApiServices
import com.android.games.domain.DetailsDataModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class DetailsRepository(private val api: ApiServices) {

    fun getGameDetails(gameId: Int) = flow {
        emit(DetailsDataModel(
            api.getGamesDetails(gameId, API_KEY),
            api.getScreenShots(gameId, API_KEY)
        ))
    }.flowOn(Dispatchers.IO)

}