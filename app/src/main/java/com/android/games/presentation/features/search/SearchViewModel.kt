package com.android.games.presentation.features.search

import androidx.compose.runtime.MutableState
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.games.data.SearchRepository
import com.android.games.domain.SearchDataModel
import com.android.games.domain.SearchHistory
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
private const val SEARCH_KEY = "SearchVmKey"

class SearchViewModel(
    private val searchRepository: SearchRepository,
    private val state: SavedStateHandle
    ) : ViewModel() {

    private val _uiState = MutableStateFlow<SearchUiState>(SearchUiState.Success())
    val uiState: StateFlow<SearchUiState> = _uiState

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _uiState.value = SearchUiState.Failure(throwable)
    }

    private var dataModel = SearchDataModel()

    fun getSearchList(searchQuery: String) {
        if (state.get<SearchUiState.Success>(SEARCH_KEY) == null) {
            viewModelScope.launch(exceptionHandler) {
                searchRepository.getSearchResults(searchQuery)
                    .onStart { _uiState.value = SearchUiState.Loading }
                    .collect { searchDataModel ->
                        _uiState.value = SearchUiState.Success(searchDataModel)
                        dataModel = searchDataModel
                    }
            }
        } else {
            _uiState.value = state.get<SearchUiState.Success>(SEARCH_KEY)!!
        }
    }

    fun saveSearchHistory(history: SearchHistory) {
        viewModelScope.launch {
            searchRepository.insertIntoHistories(history)
                .collect { histories ->
                    _uiState.value = SearchUiState.Success(histories)
                }
        }
    }

    fun deleteSearchHistory(titleId: Int) {
        viewModelScope.launch {
            searchRepository.deleteHistoryRecord(titleId)
                .collect { histories ->
                    _uiState.value = SearchUiState.Success(histories)
                }
        }
    }

    fun saveInstanceState() {
        state[SEARCH_KEY] = SearchUiState.Success(dataModel)
    }

    fun deleteInstanceState() {
        state[SEARCH_KEY] = null
    }

}