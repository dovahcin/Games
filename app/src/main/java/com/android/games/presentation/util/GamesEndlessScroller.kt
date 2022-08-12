package com.android.games.presentation.util

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GamesEndlessScroller(
    private var currentPage: Int,
    private val layoutManager: LinearLayoutManager,
    private val loadMore: (Int) -> Unit
) : RecyclerView.OnScrollListener() {

    private var loading = true
    private var previousTotal = 0
    private val visibleThreshold = 1

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)


        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

        if (loading) {
            if (totalItemCount != previousTotal) {
                loading = false
                previousTotal = layoutManager.itemCount
            }
        }

        if (isReadyToLoadMore(totalItemCount, visibleItemCount, firstVisibleItem)) {
            if (currentPage == 0)
                currentPage = 1
            currentPage++
            loadMore.invoke(currentPage)
            loading = true
        }

    }

    private fun isReadyToLoadMore(
        totalItemCount: Int,
        visibleItemCount: Int,
        firstVisibleItem: Int
    ) = !loading && (totalItemCount - visibleItemCount - firstVisibleItem <= visibleThreshold)
}