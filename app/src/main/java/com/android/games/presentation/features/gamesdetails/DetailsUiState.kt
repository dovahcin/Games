package com.android.games.presentation.features.gamesdetails

import android.os.Parcelable
import com.android.games.domain.DetailsDataModel
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

sealed class DetailsUiState() {
    @Parcelize
    data class Success(val detailsDataModel:@RawValue DetailsDataModel = DetailsDataModel()): DetailsUiState(), Parcelable
    data class Failure(val exception: Throwable): DetailsUiState()
    object Loading: DetailsUiState()
}
