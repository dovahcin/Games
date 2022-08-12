package com.android.games.presentation.features.search

import android.os.Parcelable
import com.android.games.domain.Games
import com.android.games.domain.SearchDataModel
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import java.lang.Exception

sealed class SearchUiState {
    @Parcelize
    data class Success(val searchDataModel:@RawValue SearchDataModel = SearchDataModel()): SearchUiState(), Parcelable
    data class Failure(val exception: Throwable): SearchUiState()
    object Loading: SearchUiState()
}