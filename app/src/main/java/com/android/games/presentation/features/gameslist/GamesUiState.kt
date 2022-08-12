package com.android.games.presentation.features.gameslist

import android.os.Parcelable
import com.android.games.domain.Games
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

sealed class GamesUiState {
    @Parcelize
    data class Success(val games:@RawValue Games = Games()): GamesUiState(), Parcelable
    data class Failure(val exception: Throwable): GamesUiState()
    object Loading: GamesUiState()
}
