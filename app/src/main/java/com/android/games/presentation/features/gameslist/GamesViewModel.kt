package com.android.games.presentation.features.gameslist

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.games.data.GamesRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class GamesViewModel(
    private val gamesRepository: GamesRepository,
    private val state: SavedStateHandle,
) : ViewModel() {

    companion object {
        const val GAMES_KEY = "GamesViewModel"
    }

    private val _uiState = MutableStateFlow<GamesUiState>(
        GamesUiState.Success()
    )
    val uiState: StateFlow<GamesUiState> = _uiState

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _uiState.value = GamesUiState.Failure(throwable)
    }

    fun getGamesForView(page: Int) {
        if (state.get<GamesUiState.Success>(GAMES_KEY) == null || page != 1) {
            viewModelScope.launch(exceptionHandler) {
                gamesRepository.getGames(page)
                    .onStart { _uiState.value = GamesUiState.Loading }
                    .collect { games ->
                        _uiState.value = GamesUiState.Success(games)
                        state.set(GAMES_KEY, GamesUiState.Success(games))
                    }
            }
        } else {
            _uiState.value = state.get<GamesUiState.Success>(GAMES_KEY)!!
        }
    }

}