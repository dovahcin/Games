package com.android.games.presentation.features.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.android.games.R
import com.android.games.databinding.FragmentSearchBinding
import com.android.games.domain.Result
import com.android.games.domain.SearchDataModel
import com.android.games.domain.SearchHistory
import com.android.games.presentation.features.search.adapter.SearchHistoryAdapter
import com.android.games.presentation.features.search.adapter.SearchResultsAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val searchViewModel: SearchViewModel by viewModel()

    private val searchResultClick: (Int, String) -> Unit = { gameId, searchQuery ->
        findNavController().navigate(
            SearchFragmentDirections.actionSearchFragmentToDetailsFragment(gameId)
        )
        searchViewModel.saveSearchHistory(SearchHistory(searchQuery))
        searchViewModel.saveInstanceState()
    }
    private val deleteClick: (Int) -> Unit = { titleId ->
        searchViewModel.deleteSearchHistory(titleId)
    }
    private val historyClick: (String) -> Unit = { gameTitle ->
        searchViewModel.getSearchList(gameTitle)
        binding.recyclerSearchResults.isVisible = true
        binding.recyclerHistoryResults.isVisible = false
        binding.textEditSearch.setText(gameTitle)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)

        searchViewModel.getSearchList("")

        val searchResultAdapter = SearchResultsAdapter(searchResultClick)
        val searchHistoryAdapter = SearchHistoryAdapter(deleteClick, historyClick)

        binding.recyclerSearchResults.adapter = searchResultAdapter
        binding.recyclerHistoryResults.adapter = searchHistoryAdapter

        lifecycleScope.launch {
            searchViewModel.uiState.collect { uiState ->
                when (uiState) {
                    is SearchUiState.Success -> {
                        searchResultAdapter.update(uiState.searchDataModel.games.results)
                        searchHistoryAdapter.update(uiState.searchDataModel.histories)
                    }
                    is SearchUiState.Failure -> showError(uiState.exception)
                }
                showLoadingView(uiState is SearchUiState.Loading)
            }
        }

        binding.textEditSearch.addTextChangedListener(object : TextWatcher {
            private var searchFor = ""
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.isNullOrEmpty()) {
                    binding.recyclerSearchResults.isVisible = false
                    binding.recyclerHistoryResults.isVisible = true
                    return
                } else {
                    binding.recyclerSearchResults.isVisible = true
                    binding.recyclerHistoryResults.isVisible = false
                }

                val searchText = s.toString()
                if (searchText == searchFor) {
                    return
                }

                searchFor = searchText
                lifecycleScope.launch(Dispatchers.IO) {
                    delay(500)
                    if (searchFor != searchText) {
                        return@launch
                    }
                    searchViewModel.getSearchList(searchFor)
                    searchViewModel.deleteInstanceState()
                    searchResultAdapter.update(searchFor)

                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        return binding.root
    }

    private fun showLoadingView(isVisible: Boolean) {
        binding.progressBar.isVisible = isVisible
    }

    private fun showError(exception: Throwable) {
        Snackbar.make(binding.root, exception.localizedMessage!!, 5000).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}