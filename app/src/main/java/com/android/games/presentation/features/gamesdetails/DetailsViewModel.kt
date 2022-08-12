package com.android.games.presentation.features.gamesdetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.games.data.DetailsRepository
import com.android.games.domain.DetailsDataModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val detailsRepository: DetailsRepository,
    private val state: SavedStateHandle
    ) : ViewModel() {

    companion object {
        const val DETAILS_KEY = "DetailsDataModel"
        const val FULL_FRAGMENT_STATE = "FullFragmentState"
    }

    init {
        if (state.get<Boolean>(FULL_FRAGMENT_STATE) == null)
        state[FULL_FRAGMENT_STATE] = false
    }

    private val _uiState = MutableStateFlow<DetailsUiState>(DetailsUiState.Success())
    val uiState: StateFlow<DetailsUiState> = _uiState

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _uiState.value = DetailsUiState.Failure(throwable)
    }

    fun getDetailsForView(gameId: Int) {
        if (isReadyToLoad()) {
            viewModelScope.launch(exceptionHandler) {
                detailsRepository.getGameDetails(gameId)
                    .onStart { _uiState.value = DetailsUiState.Loading }
                    .collect { dataModel ->
                        _uiState.value = DetailsUiState.Success(dataModel)
                        state[DETAILS_KEY] = DetailsUiState.Success(dataModel)
                    }
            }
        } else {
            _uiState.value = state.get<DetailsUiState.Success>(DETAILS_KEY)!!
            state[FULL_FRAGMENT_STATE] = false
        }

    }

    fun saveFullFragmentState() {
        state[FULL_FRAGMENT_STATE] = true
    }

    private fun isReadyToLoad() =
        state.get<Boolean>(FULL_FRAGMENT_STATE) == false
}