package com.android.games.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.games.domain.SearchHistory

@Database(entities = [SearchHistory::class], version = 1)
abstract class GamesDatabase : RoomDatabase() {

    abstract fun searchHistory(): SearchHistoryDao

}