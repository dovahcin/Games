package com.android.games.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.games.domain.SearchHistory

@Dao
interface SearchHistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveSearchHistory(history: SearchHistory)

    @Query("DELETE FROM history_table WHERE id = :titleId")
    suspend fun deleteSearchHistory(titleId: Int)

    @Query("SELECT * FROM history_table ORDER BY id ASC")
    suspend fun getAllHistory(): MutableList<SearchHistory>
}