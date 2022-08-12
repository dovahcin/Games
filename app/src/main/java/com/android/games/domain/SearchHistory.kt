package com.android.games.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

data class SearchDataModel(
    val games: Games = Games(),
    val histories: MutableList<SearchHistory> = mutableListOf()
)

@Entity(tableName = "history_table")
data class SearchHistory(
    var searchQuery: String = "",
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
)
