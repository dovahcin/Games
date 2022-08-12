package com.android.games.domain

data class ScreenShots(
    val count: Int = -1,
    val next: Any? = null,
    val previous: Any? = null,
    val results: MutableList<ScreenShot> = mutableListOf()
)

data class ScreenShot(
    val height: Int,
    val id: Int,
    val image: String,
    val is_deleted: Boolean,
    val width: Int
)