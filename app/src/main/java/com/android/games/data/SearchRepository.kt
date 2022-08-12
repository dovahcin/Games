package com.android.games.data

import android.util.Log
import com.android.games.data.db.GamesDatabase
import com.android.games.data.db.SearchHistoryDao
import com.android.games.data.network.API_KEY
import com.android.games.data.network.ApiServices
import com.android.games.domain.Games
import com.android.games.domain.SearchDataModel
import com.android.games.domain.SearchHistory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class SearchRepository(private val api: ApiServices, private val gamesDatabase: SearchHistoryDao) {

    fun getSearchResults(searchQuery: String) = flow {

        val queryResult = if (searchQuery != "")
            api.getSearchResults(API_KEY, searchQuery)
        else
            Games()

        val histories = gamesDatabase.getAllHistory()
        Log.d("SearchFragment", "$histories")

        emit(
            SearchDataModel(
                queryResult,
                histories
            )
        )
    }.flowOn(Dispatchers.IO)

    fun insertIntoHistories(history: SearchHistory) = flow {
        gamesDatabase.saveSearchHistory(history)
        emit(
            SearchDataModel(
                Games(),
                histories = gamesDatabase.getAllHistory()
            )
        )
    }.flowOn(Dispatchers.IO)

    fun deleteHistoryRecord(titleId: Int) = flow {
        gamesDatabase.deleteSearchHistory(titleId)
        emit(
            SearchDataModel(
                Games(),
                histories = gamesDatabase.getAllHistory()
            )
        )
    }.flowOn(Dispatchers.IO)
}