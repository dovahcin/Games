package com.android.games.presentation.features.gameslist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.games.R
import com.android.games.databinding.FragmentGamesListBinding
import com.android.games.domain.Games
import com.android.games.presentation.features.gameslist.adpater.VerticalGamesAdapter
import com.android.games.presentation.util.GamesEndlessScroller
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class GamesListFragment : Fragment(), VerticalGamesAdapter.ItemClick {
    private var _binding: FragmentGamesListBinding? = null
    private val binding get() = _binding!!

    private val defaultPage = 1

    private val gamesViewModel: GamesViewModel by viewModel()

    private val gamesAdapter = VerticalGamesAdapter(this)

    private var currentPage = gamesAdapter.itemCount / 20


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_games_list, container, false)


        binding.recyclerGamesVertical.apply {
            adapter = gamesAdapter
            addOnScrollListener(GamesEndlessScroller(currentPage, layoutManager as LinearLayoutManager) { page ->
                gamesViewModel.getGamesForView(page)
                currentPage = page
            })
        }

        gamesViewModel.getGamesForView(defaultPage)

        lifecycleScope.launch {
            gamesViewModel.uiState.collect { uiState ->
                when (uiState) {
                    is GamesUiState.Success -> showGames(uiState.games)
                    is GamesUiState.Failure -> showError(uiState.exception)
                }
                showLoadingView(uiState is GamesUiState.Loading)
            }
        }

        binding.searchBox.setOnClickListener {
            findNavController().navigate(
                GamesListFragmentDirections.actionGamesListFragmentToSearchFragment()
            )
        }

        return binding.root
    }

    private fun showLoadingView(isVisible: Boolean) {
        binding.progressBar.isVisible = isVisible
    }

    private fun showError(exception: Throwable) {
        Snackbar.make(binding.root, exception.localizedMessage!!, 10000).show()
    }

    private fun showGames(games: Games) {
        gamesAdapter.update(games.results)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onItemClicked(gameId: Int) {
        findNavController().navigate(
            GamesListFragmentDirections.actionGamesListFragmentToDetailsFragment(gameId)
        )
    }
}